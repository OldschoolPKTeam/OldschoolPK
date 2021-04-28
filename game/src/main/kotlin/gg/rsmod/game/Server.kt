package gg.rsmod.game

import com.google.common.base.Stopwatch
import gg.rsmod.game.configuration.WorldConfiguration
import gg.rsmod.game.model.Tile
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.GroundItem
import gg.rsmod.game.model.entity.Npc
import gg.rsmod.game.model.skill.SkillSet
import gg.rsmod.game.protocol.ClientChannelInitializer
import gg.rsmod.game.service.GameService
import gg.rsmod.game.service.rsa.RsaService
import gg.rsmod.game.service.xtea.XteaKeyService
import gg.rsmod.util.ServerProperties
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import mu.KLogging
import net.runelite.cache.fs.Store
import java.net.InetSocketAddress
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

/**
 * The [Server] is responsible for starting any and all games.
 *
 * @author Tom <rspsmods@gmail.com>
 */
class Server {

    private val acceptGroup = NioEventLoopGroup(2)

    private val ioGroup = NioEventLoopGroup(1)

    val bootstrap = ServerBootstrap()

    /**
     * Prepares and handles any game related logic that was specified by the
     * user.
     *
     * Due to being decoupled from the API logic that will always be used, you
     * can start multiple servers with different game property files.
     */
    fun start(
            filestore: Path,
            worldConfigurationPath: Path,
            packets: Path,
            blocks: Path,
            devProps: Path?,
            devWorld: Boolean,
            args: Array<String>
    ): World {
        Thread.setDefaultUncaughtExceptionHandler { t, e -> Server.logger.error("Uncaught server exception in thread $t!", e) }

        val stopwatch = Stopwatch.createStarted()
        val individualStopwatch = Stopwatch.createUnstarted()

        /*
         * Load the dev property file.
         */
        val devProperties = ServerProperties()
        if (devProps != null && Files.exists(devProps)) {
            devProperties.loadYaml(devProps.toFile())
        }

        val worldConfiguration = WorldConfiguration(Paths.get("data", "worlds"))
        worldConfiguration.load(worldConfigurationPath)

        logger.info("Loaded properties for ${worldConfiguration.get<String>("name")!!}: $worldConfigurationPath")

        /*
         * Create a game context for our configurations and services to run.
         */
        val gameContext = GameContext(name = worldConfiguration.get<String>("name")!!,
                revision = worldConfiguration.get<Int>("revision")!!,
                cycleTime = worldConfiguration.getOrDefault("cycle-time", 600),
                playerLimit = worldConfiguration.getOrDefault("max-players", 2048),
                home = Tile(worldConfiguration.get<Int>("home-x")!!, worldConfiguration.get<Int>("home-z")!!, worldConfiguration.getOrDefault("home-height", 0)),
                skillCount = worldConfiguration.getOrDefault("skill-count", SkillSet.DEFAULT_SKILL_COUNT),
                npcStatCount = worldConfiguration.getOrDefault("npc-stat-count", Npc.Stats.DEFAULT_NPC_STAT_COUNT),
                runEnergy = worldConfiguration.getOrDefault("run-energy", true),
                gItemPublicDelay = worldConfiguration.getOrDefault("gitem-public-spawn-delay", GroundItem.DEFAULT_PUBLIC_SPAWN_CYCLES),
                gItemDespawnDelay = worldConfiguration.getOrDefault("gitem-despawn-delay", GroundItem.DEFAULT_DESPAWN_CYCLES),
                preloadMaps = worldConfiguration.getOrDefault("preload-maps", false),
                pluginExtensions = worldConfiguration.get<ArrayList<LinkedHashMap<String, String>>>("plugins")!!.map { it["extension"]!! }.toList(),
                beta = worldConfiguration.getOrDefault("beta", false),
                allowCombatSkillSet = worldConfiguration.getOrDefault("combat-skill-set", false),
                allowNonCombatSkillSet = worldConfiguration.getOrDefault("non-combat-skill-set", false),
                allowItemSpawning = worldConfiguration.getOrDefault("spawn-items", false),
                assertScriptBinding = worldConfiguration.getOrDefault("assert-script-bind", true),
                devWorld = devWorld)

        val devContext = DevContext(
                debugExamines = devProperties.getOrDefault("debug-examines", false),
                debugObjects = devProperties.getOrDefault("debug-objects", false),
                debugButtons = devProperties.getOrDefault("debug-buttons", false),
                debugItemActions = devProperties.getOrDefault("debug-items", false),
                debugMagicSpells = devProperties.getOrDefault("debug-spells", false))

        val world = World(gameContext, devContext)

        /*
         * Load the file store.
         */
        individualStopwatch.reset().start()
        world.filestore = Store(filestore.toFile())
        world.filestore.load()
        logger.info("Loaded filestore from path {} in {}ms.", filestore, individualStopwatch.elapsed(TimeUnit.MILLISECONDS))

        /*
         * Load the definitions.
         */
        world.definitions.loadAll(world.filestore)

        /*
         * Load the services required to run the server.
         */
        world.loadServices(this, worldConfiguration)
        world.init()

        if (gameContext.preloadMaps) {
            /*
             * Preload region definitions.
             */
            world.getService(XteaKeyService::class.java)?.let { service ->
                world.definitions.loadRegions(world, world.chunks, service.validRegions)
            }
        }

        /*
         * Load the packets for the game.
         */
        world.getService(type = GameService::class.java)?.let { gameService ->
            individualStopwatch.reset().start()
            gameService.messageStructures.load(packets.toFile())
            gameService.messageEncoders.init()
            gameService.messageDecoders.init(gameService.messageStructures)
            logger.info("Loaded message codec and handlers in {}ms.", individualStopwatch.elapsed(TimeUnit.MILLISECONDS))
        }

        /*
         * Load the update blocks for the game.
         */
        individualStopwatch.reset().start()
        world.loadUpdateBlocks(blocks.toFile())
        logger.info("Loaded update blocks in {}ms.", individualStopwatch.elapsed(TimeUnit.MILLISECONDS))

        /*
         * Load the privileges for the game.
         */
        individualStopwatch.reset().start()
        world.privileges.load(worldConfiguration)
        logger.info("Loaded {} privilege levels in {}ms.", world.privileges.size(), individualStopwatch.elapsed(TimeUnit.MILLISECONDS))

        /*
         * Load the plugins for game content.
         */
        individualStopwatch.reset().start()
        world.plugins.init(
                server = this, world = world,
                jarPluginsDirectory = worldConfiguration.getOrDefault("plugin-packed-path", "./plugins"))
        logger.info("Loaded {} plugins in {}ms.", DecimalFormat().format(world.plugins.getPluginCount()), individualStopwatch.elapsed(TimeUnit.MILLISECONDS))

        /*
         * Post load world.
         */
        world.postLoad()

        /*
         * Inform the time it took to load up all non-network logic.
         */
        logger.info("${worldConfiguration.get<String>("name")!!} loaded up in ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}ms.")

        /*
         * Set our bootstrap's groups and parameters.
         */
        val rsaService = world.getService(RsaService::class.java)
        val clientChannelInitializer = ClientChannelInitializer(revision = gameContext.revision,
                rsaExponent = rsaService?.getExponent(), rsaModulus = rsaService?.getModulus(),
                filestore = world.filestore, world = world)

        bootstrap.group(acceptGroup, ioGroup)
        bootstrap.channel(NioServerSocketChannel::class.java)
        bootstrap.childHandler(clientChannelInitializer)
        bootstrap.option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_KEEPALIVE, true)

        /*
         * Bind all service networks, if applicable.
         */
        world.bindServices(this)

        /*
         * Bind the game port.
         */
        val port = worldConfiguration.getOrDefault("game-port", 43594)
        bootstrap.bind(InetSocketAddress(port)).sync().awaitUninterruptibly()
        logger.info("Now listening for incoming connections on port $port...")

        System.gc()

        return world
    }

    companion object : KLogging()
}
