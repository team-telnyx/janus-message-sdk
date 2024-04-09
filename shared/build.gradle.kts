import java.io.BufferedReader
import java.io.InputStreamReader

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.20"
    id("co.touchlab.kmmbridge") version "0.5.2"
    id("maven-publish")
}

kmmbridge {
    mavenPublishArtifacts()
    spm()
    //cocoapodsTrunk()
    //etc
}


@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    withSourcesJar(publish = true)


    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()


    version = "0.7.34"

    cocoapods {
        summary = "Janus-message-sdk"
        name = "JanusMessageSDK"
        homepage = "https://github.com/team-telnyx/janus-message-sdk"
        version = "${project.version}"
        license = "{ :type => 'MIT', :text => 'MIT License\n" +
                "\n" +
                "Copyright (C) 2021 Telnyx, Inc. (https://www.telnyx.com)\n" +
                "\n" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                "of this software and associated documentation files (the \"Software\"), to deal\n" +
                "in the Software without restriction, including without limitation the rights\n" +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                "copies of the Software, and to permit persons to whom the Software is\n" +
                "furnished to do so, subject to the following conditions:\n" +
                "\n" +
                "The above copyright notice and this permission notice shall be included in all\n" +
                "copies or substantial portions of the Software.\n" +
                "\n" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                "SOFTWARE.' }"
        authors =  "{ 'Telnyx' => 'cocoapods@telnyx.com' }"
        source = "{ :http => 'https://github.com/team-telnyx/janus-message-sdk/releases/download/${project.version}/JanusMessageSdk.xcframework.zip', :type => 'zip' }"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "JanusMessageSDK"
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
                implementation(libs.ktor.client.websockets)
                implementation("app.softwork:kotlinx-uuid-core:0.0.22")
                implementation("co.touchlab:kermit:2.0.3") //Add latest version
            }
        }
        val androidMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:2.3.7")

             //Add latest version
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
val podSpecFile =
    "/Users/runner/work/janus-message-sdk/janus-message-sdk/shared/build/cocoapods/publish/release/JanusMessageSDK.podspec"

 project.tasks.register("pushRemotePodspec") {
   // group = co.touchlab.faktory.TASK_GROUP_NAME

    inputs.files(podSpecFile)

    @Suppress("ObjectLiteralToLambda")
    doLast(object : Action<Task>{
        override fun execute(t: Task) {
            val extras = mutableListOf<String>()

            extras.add("--allow-warnings")
            extras.add("--verbose")

            project.procRunFailLog("pod", "trunk", "push", podSpecFile, *extras.toTypedArray())
        }
    })
}

fun Project.procRunFailLog(vararg params: String):List<String>{
    val output = mutableListOf<String>()
    try {
        logger.info("Project.procRunFailLog: ${params.joinToString(" ")}")
        procRun(*params) { line, _ -> output.add(line) }
    } catch (e: Exception) {
        output.forEach { logger.error("error: $it") }
        throw e
    }
    return output
}
 fun procRun(vararg params: String, processLines: (String, Int) -> Unit) {
    val process = ProcessBuilder(*params)
        .redirectErrorStream(true)
        .start()

    val streamReader = InputStreamReader(process.inputStream)
    val bufferedReader = BufferedReader(streamReader)
    var lineCount = 1

    bufferedReader.forEachLine { line ->
        processLines(line, lineCount)
        lineCount++
    }

    bufferedReader.close()
    val returnValue = process.waitFor()
    if(returnValue != 0)
        throw GradleException("Process failed: ${params.joinToString(" ")}")
}



publishing {
    publications {

        create<MavenPublication>("maven") {
            run {
                groupId = "com.telnyx"
                artifactId = this@Build_gradle.getArtifactId()
                version = getVersionName()
                artifact(tasks["sourceJar"])

            }
            pom {
            }
        }
    }

    repositories {
        mavenLocal()
    }
}