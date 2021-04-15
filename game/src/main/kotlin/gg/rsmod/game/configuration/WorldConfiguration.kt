package gg.rsmod.game.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.nio.file.Path

/**
 * The configuration of the world.
 * @param configurationPath Path to the folder that holds configuration files.
 */
class WorldConfiguration(private val configurationPath: Path = Path.of("")) {

    val properties = hashMapOf<String, Any>()

    /**
     * Get a property from the loaded configuration.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? = properties[key] as? T

    /**
     * Gets the property associated with the [key]. If it cannot be found, it will
     * return the [default] value instead.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getOrDefault(key: String, default: T): T = properties[key] as? T ?: default

    /**
     * Checks if [properties] contains a value associated with [key].
     */
    fun has(key: String): Boolean = properties.containsKey(key)

    /**
     * Loads the .yml file and all it's defined properties.
     * @param filePath the configuration file's path relative to the [configurationPath].
     */
    fun load(filePath: Path) {
        val file = configurationPath.resolve(filePath).toFile()
        val mapper = ObjectMapper(YAMLFactory())
        val map = mapper.readValue(file, HashMap<String, Any>().javaClass)
        val configProperties = map.map { WorldConfigurationProperty(it.key, it.value) }

        // Load template first so this file's extensions/overwrites apply after.
        val template = configProperties.singleOrNull { it.key == "template" }
        if (template != null) {
            load(Path.of(template.value as String))
        }

        configProperties.forEach { loadProperty(it) }
    }

    /**
     * Load the property and apply extensions as needed.
     */
    @Suppress("UNCHECKED_CAST")
    private fun loadProperty(property: WorldConfigurationProperty) {
        if (property.isList()) {
            val listValues = property.listValues(includeExtend = false)
            if (property.isExtending()) {
                val extend = if (properties.containsKey(property.key)) {
                    (properties[property.key] as List<Any>).toMutableList()
                } else mutableListOf()
                extend.addAll(listValues)
                properties[property.key] = extend
                return
            }

            properties[property.key] = listValues
            return
        }

        properties[property.key] = property.value
    }
}