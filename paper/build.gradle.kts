plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.1.11"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

dependencies {
    implementation(project(":api"))
    implementation(project(":core"))
    paperDevBundle("1.17.1-R0.1-SNAPSHOT")
    implementation("net.kyori", "adventure-platform-bukkit", "4.0.0-SNAPSHOT")
    implementation("io.undertow", "undertow-core", "2.2.3.Final")
    implementation("org.bstats", "bstats-bukkit", "2.2.1")
}

tasks {
    build {
        dependsOn(reobfJar)
    }
    shadowJar {
        minimize {
            exclude(dependency("io.undertow:.*:.*"))
        }
        listOf(
            "net.kyori",
            "org.bstats"
        ).forEach { relocate(it, "${rootProject.group}.lib.$it") }
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
