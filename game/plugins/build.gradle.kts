val rootPluginDir = projectDir
val rootPluginBuildDir = buildDir

subprojects {
	group = pluginPackage
}

allprojects {
    val relative = projectDir.relativeTo(rootPluginDir)
    buildDir = rootPluginBuildDir.resolve(relative)
}
