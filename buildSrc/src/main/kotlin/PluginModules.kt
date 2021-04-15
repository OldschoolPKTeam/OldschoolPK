import java.io.File

const val pluginPackage = "gg.rsmod.plugins"
const val PLUGIN_DIRECTORY = "./game/plugins/"

val pluginModules: List<String> by lazy {
    val moduleDirectories = search(File(PLUGIN_DIRECTORY))
    val pluginRoot = PLUGIN_DIRECTORY.replace(".", "")
    val moduleRegex = (pluginRoot.replace("/", "\\/") + "(.+)").toRegex()

    moduleDirectories.map {
        val matchResult = moduleRegex.find(it.absolutePath)
        val moduleName = matchResult?.groupValues?.getOrElse(1) { null }
        "$pluginRoot$moduleName".replace("/", ":")
    }.toList()
}

private fun search(directory: File): List<File> {
    check(directory.exists()) { "$PLUGIN_DIRECTORY does not exist." }
    check(directory.isDirectory) { "$PLUGIN_DIRECTORY is not a directory." }
    val moduleDirectories = mutableListOf<File>()
    val files = directory.listFiles()

    files?.forEach { file ->
        if (file.isModule()) {
            moduleDirectories.add(file)
            //moduleDirectories.addAll(search(file))
        } else if (file.isDirectory) {
            moduleDirectories.addAll(search(file))
        }
    }

    return moduleDirectories.toList()
}

private fun File.isModule(): Boolean {
    return isDirectory && name != "template"
            && listFiles()?.any { it.name == "src" } == true
            && listFiles()?.any { it.name.startsWith("build.gradle") } == true
}