plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.22"
    id("co.touchlab.kmmbridge") version "0.5.2"
    id("maven-publish")
}
version = "0.6.4"

kmmbridge {
    mavenPublishArtifacts()
    spm()
    cocoapodsTrunk()
    //etc
}
addGithubPackagesRepository()


@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()



    cocoapods {
        summary = "Janus-message-sdk"
        name = "JanusMessageSDK"
        homepage = "https://github.com/team-telnyx/janus-message-sdk"
        version = "0.6.2"
        license = "{ :type => 'MIT', :file => 'LICENSE' }"
        authors =  "{ 'Telnyx' => 'cocoapods@telnyx.com' }"
        source = "{ :git => 'https://github.com/team-telnyx/janus-message-sdk.git', :tag => '#{spec.version}' }"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.auth)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.kotlinx.datetime)
                implementation(libs.ktor.client.content.negotiation)
                implementation("app.softwork:kotlinx-uuid-core:0.0.22")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.telnyx.janusmessagesdk"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}



fun getVersionName(): String {
    return version.toString() // Replace with version Name
}

fun getArtifactId(): String {
    return "janus-message-sdk" // Replace with library name ID
}

tasks.register<Jar>(name = "sourceJar") {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sources")
}


publishing {
    publications {

        create<MavenPublication>("maven") {
            run {
                groupId = "com.telnyx"
                artifactId = this@Build_gradle.getArtifactId()
                version = getVersionName()
                //artifact("$buildDir/outputs/aar/${this@Build_gradle.getArtifactId()}-${getVersionName()}-release.aar")
                artifact(tasks["sourceJar"])
                artifact("$buildDir/outputs/aar/${project.getName()}-release.aar")

            }
            pom {
            }
        }
    }

    repositories {
        mavenLocal()
    }
}