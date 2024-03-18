enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
        mavenLocal()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        jcenter()
        mavenCentral()
        mavenLocal()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "JanusMessageSdk"
include(":androidApp")
include(":shared")