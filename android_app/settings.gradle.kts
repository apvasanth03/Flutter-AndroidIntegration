import java.io.FileInputStream
import java.net.URI
import java.util.Properties

val localProperties = Properties()
localProperties.load(layout.rootDirectory.file("local.properties").asFile.inputStream())

val isUseLocalFlutterFSModule =
    localProperties.getProperty("is_use_local_flutter_fs_module")?.toBoolean() ?: false

if (isUseLocalFlutterFSModule) {
    apply { from("flutter_settings.gradle") }
}

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
    val githubProperties = Properties()
    if (file("./github.properties").exists()) {
        githubProperties.load(FileInputStream(file("./github.properties")))
    }

    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = URI.create("https://storage.googleapis.com/download.flutter.io")
        }

        if (!isUseLocalFlutterFSModule) {
            // Use Published Flutter Module
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/apvasanth03/Flutter-AndroidIntegration")
                credentials {
                    username = githubProperties.getProperty("gpr.usr")
                    password = githubProperties.getProperty("gpr.key")
                }
            }
        }
    }
}

rootProject.name = "Android App"
include(":app")
