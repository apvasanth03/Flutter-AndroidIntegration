import java.net.URI
import java.nio.file.Paths

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    val storageUrl = "https://storage.googleapis.com"
    val flutterHome = System.getenv("FLUTTER_HOME")
    val engineRealm = Paths.get(flutterHome, "bin", "internal", "engine.realm")
        .toFile().readText().trim()

    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = URI.create("${storageUrl}/${engineRealm}download.flutter.io")
        }
    }
}

rootProject.name = "Publish Pigeon"
include(":app")
 