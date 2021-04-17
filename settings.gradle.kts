rootProject.name = "gg.rsmod"
include(":util")
include(":net")
include(":game")
include(":game:plugins")

pluginModules.forEach {
    include(it)
}