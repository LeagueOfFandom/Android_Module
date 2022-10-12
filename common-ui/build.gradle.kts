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

    api(project(":foundation"))
    implementation(project(":core-model"))

    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.androidX.material)
    implementation(Dependencies.androidX.constraintLayout)
    implementation(Dependencies.androidX.dataStore)
    implementation(Dependencies.androidX.navigation.ui)
    implementation(Dependencies.androidX.navigation.fragment)

    api(Dependencies.kotlin.coroutine)


    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.androidX.junit)
    androidTestImplementation(Dependencies.AndroidTest.espressoCore)


    //Glide
    implementation(Dependencies.glide.android)
    implementation(Dependencies.glide.annotationProcessor)

    // Youtube
    implementation(Dependencies.library.youtubeCore)
    implementation(Dependencies.library.youtubeChromeCast)

    // Indicator
    implementation(Dependencies.library.indicator)
}