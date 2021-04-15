rootProject.name = "gg.rsmod"
include(":util")
include(":net")
include(":game")

pluginModules.forEach {
    include(it)
}