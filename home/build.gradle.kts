plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
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
        dataBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":common-ui"))
    implementation(project(":core-model"))


    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.androidX.material)
    implementation(Dependencies.androidX.constraintLayout)
    implementation(Dependencies.androidX.fragment)
    implementation(Dependencies.androidX.lifecycle.viewModel)
    implementation(Dependencies.androidX.navigation.ui)
    implementation(Dependencies.androidX.navigation.fragment)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.androidX.junit)
    androidTestImplementation(Dependencies.AndroidTest.espressoCore)

    // Hilt
    androidTestImplementation(Dependencies.androidTest.hilt)
    kaptAndroidTest(Dependencies.hilt.compiler)
    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

    // Indicator
    implementation(Dependencies.library.indicator)
}