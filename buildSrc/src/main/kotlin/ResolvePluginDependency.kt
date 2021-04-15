import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

private val moduleList: List<Pair<String, String>> by lazy {
    pluginModules.map { Pair(it.substring(it.lastIndexOf(":") + 1), it) }
}

private val duplicates by lazy {
    moduleList.groupingBy { it.first }.eachCount().filter { it.value > 1 }
}

/**
 * Resolve a module reference based on it's simple name.
 * @param name The simple, unqualified name of the module.
 * @param configurationName Dependency configuration (e.g. implementation, api, etc).
 * @throws IllegalStateException if there are multiple modules with the same simple name
 * @throws IllegalArgumentException if no module is found with the simple name provided
 * @return The full module path (ex: "shops" would return ":game:plugins:shops")
 */
fun DependencyHandler.plugin(name: String, configurationName: String = "implementation") {
    check (duplicates.isEmpty()) { "Duplicate plugin module name: ${duplicates.keys.first()}" }
    val path = moduleList.toMap()[name] ?: throw IllegalArgumentException("No module resolved for name: $name")
    add(configurationName, project(path))
}