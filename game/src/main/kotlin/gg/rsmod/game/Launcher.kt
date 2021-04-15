package gg.rsmod.game

import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import java.nio.file.Path
import java.nio.file.Paths

object Launcher {

    @JvmStatic
    fun main(args: Array<String>) {
        val options = Options()
        options.addOption("c", "configuration", true, "Defines the world configuration file.")
        options.addOption("d", "developer", false, "Define this runtime as development oriented.")

        val parser = DefaultParser()
        val cmd = parser.parse(options, args)

        val worldConfiguration = (if (cmd.hasOption("c")) cmd.getOptionValue("c") else "pk") + ".yml"
        val dev = cmd.hasOption("d")

        val server = Server()
        server.start(
                filestore = Paths.get("./data", "cache", "cache/"),
                worldConfigurationPath = Path.of(worldConfiguration),
                packets = Paths.get("./data", "packets.yml"),
                blocks = Paths.get("./data", "blocks.yml"),
                devProps = Paths.get("./dev-settings.yml"),
                devWorld = dev,
                args = args)
    }
}