plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.1.11"
    id("xyz.jpenilla.run-paper") version "1.0.4"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "net.pl3x.map"
version = "2.0-SNAPSHOT"
description = "Minimalistic and lightweight world map viewer for Paper servers"

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    mavenCentral()
}

dependencies {
    paperDevBundle("1.17.1-R0.1-SNAPSHOT")
    implementation("net.kyori", "adventure-text-minimessage", "4.1.0-SNAPSHOT") {
        isTransitive = false
    }
    implementation("io.undertow", "undertow-core", "2.2.3.Final")
    implementation("org.bstats", "bstats-bukkit", "2.2.1")
}

tasks {
    build {
        dependsOn(reobfJar)
    }
    shadowJar {
        archiveFileName.set("${rootProject.name}-${rootProject.version}-mojang-mapped.jar")
        archiveClassifier.set("mojang-mapped")
        from(rootProject.projectDir.resolve("LICENSE"))
        minimize {
            exclude(dependency("io.undertow:.*:.*")) // does not like being minimized _or_ relocated (xnio errors)
        }
        listOf(
            "net.kyori.adventure.text.minimessage",
            "org.bstats"
        ).forEach { relocate(it, "${rootProject.group}.lib.$it") }
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(16)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
    processResources {
        expand(
            "name" to rootProject.name,
            "group" to project.group,
            "version" to project.version,
            "description" to project.description
        )
    }
}
