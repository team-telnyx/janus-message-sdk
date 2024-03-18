plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)

}

android {
    namespace = "com.telnyx.janusmessagesdk.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.telnyx.janusmessagesdk.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
            isJniDebuggable = true
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // Default ProGuard rules for Android optimization
                "proguard-rules.pro" // Custom ProGuard rules file
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
    testCoverage {
        this.jacocoVersion = "0.8.11" // Use the latest version compatible with your setup
    }


    testOptions {
        unitTests.all {

        }
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(projects.shared)
    implementation("org.jacoco:org.jacoco.agent:0.8.11:runtime")
    implementation("org.jacoco:jacoco-maven-plugin:0.8.11")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation("com.github.team-telnyx:telnyx-meet-android-sdk:0.3.1@aar") {
        isTransitive = true
    }
    implementation("androidx.core:core-ktx:+")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.1.0")
    testImplementation("org.mockito:mockito-inline:4.1.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    testImplementation("org.junit.platform:junit-platform-runner:1.0.2")
    testImplementation("commons-codec:commons-codec:1.12")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")
    testImplementation("io.mockk:mockk:1.12.1")
    testImplementation("io.mockk:mockk-agent-jvm:1.12.1")

    // Acceptance Testing
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.1.0")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test:rules:1.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.2.1")
    androidTestImplementation("androidx.test:core:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")
    androidTestImplementation("io.mockk:mockk-android:1.12.1") // Using the same version for simplicity
    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    androidTestImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    androidTestImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
}

repositories {
    google()
    gradlePluginPortal()
    jcenter()
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
}