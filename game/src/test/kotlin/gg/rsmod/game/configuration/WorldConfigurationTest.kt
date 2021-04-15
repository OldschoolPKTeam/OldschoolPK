package gg.rsmod.game.configuration

import java.nio.file.Path
import kotlin.test.Test

class WorldConfigurationTest {

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `test loading`() {
        val config = WorldConfiguration(Path.of(Path.of("").toAbsolutePath().toString(), "..", "data", "worlds"))
        config.load(Path.of("pk.yml"))
        val list = config.properties["plugins"] as ArrayList<LinkedHashMap<String, String>>
        val plugins = listOf("plugin", "pk")
        list.all { plugins.any { ext -> it["extension"] == ext } }
    }

}