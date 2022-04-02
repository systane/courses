rootProject.name = "posterrbff"
include("domain", "service")

rootProject.children.forEach {
    val projectDirName = "modules/${it.name}"
    it.projectDir = File(projectDirName)
}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
