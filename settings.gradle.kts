pluginManagement {
    repositories {
        maven("https://repo.pl3x.net/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://maven.fabricmc.net/")
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Pl3xMap"

include(":api")
include(":core")
include(":fabric")
include(":paper")
