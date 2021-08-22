plugins {
    `java-library`
}

allprojects {
    val buildnum = System.getenv("BUILD_NUMBER") ?: "SNAPSHOT"
    group = rootProject.group
    version = "${rootProject.version}-$buildnum"
    description = rootProject.description
}

subprojects {
    apply<JavaLibraryPlugin>()

    java {
        targetCompatibility = JavaVersion.VERSION_16
        sourceCompatibility = JavaVersion.VERSION_16
    }

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        implementation("net.kyori", "adventure-api", "4.8.1")
        implementation("net.kyori", "adventure-text-minimessage", "4.2.0-SNAPSHOT")
    }

    tasks {
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
    }
}
