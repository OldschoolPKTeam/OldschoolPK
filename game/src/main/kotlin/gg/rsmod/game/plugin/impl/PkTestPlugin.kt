package gg.rsmod.game.plugin.impl

import gg.rsmod.game.Server
import gg.rsmod.game.model.World
import gg.rsmod.game.plugin.KotlinPlugin
import gg.rsmod.game.plugin.KotlinPluginConfiguration
import gg.rsmod.game.plugin.PluginRepository
import kotlin.script.experimental.annotations.KotlinScript

/**
 * Represents a KotlinScript plugin.
 *
 * @author Tom <rspsmods@gmail.com>
 */
@KotlinScript(
        displayName = "Pk Test Kotlin Plugin",
        fileExtension = "pk.test.kts",
        compilationConfiguration = KotlinPluginConfiguration::class
)
abstract class PkTestPlugin(r: PluginRepository, world: World, server: Server) : KotlinPlugin(r, world, server)