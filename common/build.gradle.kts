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

    api(project(":foundation"))

    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.material)
    implementation(Dependencies.androidX.appCompat)
    
    implementation(Dependencies.androidX.dataStore)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.androidX.junit)
    androidTestImplementation(Dependencies.AndroidTest.espressoCore)


    //EventBus
    api(Dependencies.library.eventBus)

    // Hilt
    androidTestImplementation(Dependencies.androidTest.hilt)
    kaptAndroidTest(Dependencies.hilt.compiler)
    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)
}