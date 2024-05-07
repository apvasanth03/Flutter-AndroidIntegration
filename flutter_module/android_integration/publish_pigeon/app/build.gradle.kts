import org.gradle.jvm.tasks.Jar
import java.nio.file.Paths

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
}

// region ANDROID
android {
    namespace = "io.github.apvasanth03.publishpigeon"
    compileSdk = 34

    sourceSets {
        getByName("main").java.srcDir("../../../pigeon/generated/android/src")
    }

    defaultConfig {
        applicationId = "io.github.apvasanth03.publishpigeon"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
// endregion

// region TASKS
tasks.register<Jar>("buildPiegonJar") {
    from(tasks.getByName("compileReleaseKotlin"))
    from(android.sourceSets["main"].java.srcDirs)
    archiveFileName.set("piegon.jar")
    destinationDirectory.set(layout.buildDirectory.dir("outputs/jar"))
}
// endregion

// region MAVEN_PUBLISH
publishing {
    repositories {
        maven {
            val mavenUrl =
                if (project.hasProperty("maven-url")) project.property("maven-url") as String else "Unknown"
            val mavenUser =
                if (project.hasProperty("maven-url")) project.property("maven-user") as String else "Unknown"
            val mavenPwd =
                if (project.hasProperty("maven-url")) project.property("maven-pwd") as String else "Unknown"

            name = "GitHubPackages"
            url = uri(mavenUrl)
            credentials {
                username = mavenUser
                password = mavenPwd
            }
        }
    }
    publications {
        register<MavenPublication>("maven") {
            val buildNumber =
                if (project.hasProperty("buildNumber")) project.property("buildNumber") as String else "1.0.0"

            groupId = "io.github.apvasanth03"
            artifactId = "piegon"
            version = buildNumber
            artifact(tasks.named("buildPiegonJar"))
        }
    }
}
// endregion

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Flutter
    val flutterHome = System.getenv("FLUTTER_HOME")
    val engineVersion = Paths.get(flutterHome, "bin", "internal", "engine.version")
        .toFile().readText().trim()
    compileOnly("io.flutter:flutter_embedding_release:1.0.0-$engineVersion")
}