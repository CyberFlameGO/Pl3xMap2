plugins {
    id("fabric-loom") version "0.9-SNAPSHOT"
}

repositories {
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    implementation(project(":api"))
    implementation(project(":core"))
    minecraft("com.mojang:minecraft:1.17.1")
    mappings("net.fabricmc:yarn:1.17.1+build.29:v2")
    modImplementation("net.fabricmc:fabric-loader:0.11.6")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.37.1+1.17")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.6.3+kotlin.1.5.21")
    modImplementation("me.lucko:fabric-permissions-api:0.1-SNAPSHOT")
    modImplementation("com.terraformersmc:modmenu:2.0.4")
    modImplementation(include("net.kyori", "adventure-api", "4.8.1"))
    modImplementation(include("net.kyori", "adventure-platform-fabric", "4.1.0-SNAPSHOT"))
    modImplementation(include("net.kyori", "adventure-text-minimessage", "4.2.0-SNAPSHOT"))
    include(project(":api"))
    include(project(":core"))
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") { expand(mutableMapOf("version" to project.version)) }
    }
    loom {
        @Suppress("UnstableApiUsage")
        mixin.defaultRefmapName.set("pl3xmap.refmap.json")
        accessWidenerPath.set(file("src/main/resources/pl3xmap.accesswidener"))

        // todo find kotlin version of this
        /*runs {
            client {
                programArgs("--username", "BillyGalbreath")
            }
        }*/
    }
}
