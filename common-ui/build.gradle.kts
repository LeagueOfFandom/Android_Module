plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
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

    api(project(":core-network"))
    implementation(project(":core-model"))

    api(Dependencies.androidX.core)
    api(Dependencies.androidX.appCompat)
    api(Dependencies.androidX.material)
    api(Dependencies.androidX.constraintLayout)
    api(Dependencies.androidX.dataStore)
    api(Dependencies.androidX.navigation.ui)
    api(Dependencies.androidX.navigation.fragment)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.androidX.junit)
    androidTestImplementation(Dependencies.AndroidTest.espressoCore)


    //Glide
    api(Dependencies.glide.android)
    api(Dependencies.glide.annotationProcessor)

    // Youtube
    implementation(Dependencies.library.youtubeCore)
    implementation(Dependencies.library.youtubeChromeCast)

    // Indicator
    api(Dependencies.library.indicator)
}