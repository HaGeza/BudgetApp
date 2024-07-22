plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.sonarqube") version "5.1.0.4882"
}

android {
    namespace = "com.example.budgetapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.budgetapp"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.budgetapp.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.kotlinx.serialization.json.v171)
    implementation(libs.androidx.runner)

    // Local Testing
    testImplementation(libs.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)

    // Instrumentation Testing
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing.v280beta04)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation
    // Java language implementation
    implementation(libs.navigation.ui.v280beta04)

    // Kotlin
    implementation(libs.androidx.navigation.fragment.ktx.v280beta04)
    implementation(libs.androidx.navigation.ui.ktx.v280beta04)

    // Feature module Support
    implementation(libs.androidx.navigation.dynamic.features.fragment.v280beta04)

    // Jetpack Compose Integration
    implementation(libs.androidx.navigation.compose.v280beta04)

    //-------------
    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    //-------------
    // Room
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    kapt(libs.room.compiler)
}

kapt {
    correctErrorTypes = true
}

sonar {
    properties {
        property("sonar.projectKey", "HaGeza_BudgetApp")
        property("sonar.organization", "hageza")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
