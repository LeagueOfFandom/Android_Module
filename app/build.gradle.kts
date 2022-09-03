plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.soma.lof"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
        dataBinding = true
    }
}

dependencies {

    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.test.junit)
    testImplementation(Dependencies.test.mockito)
    testImplementation(Dependencies.test.coroutine)

    androidTestImplementation(Dependencies.androidTest.runner)
    androidTestImplementation(Dependencies.androidTest.rules)
    androidTestImplementation(Dependencies.androidTest.junit)
    androidTestImplementation(Dependencies.androidTest.espressoCore)
    androidTestImplementation(Dependencies.androidTest.espressoContribute)
    androidTestImplementation(Dependencies.androidTest.mockito)
    debugImplementation(Dependencies.androidTest.fragment)
    androidTestImplementation(Dependencies.androidTest.hilt)
    kaptAndroidTest(Dependencies.hilt.compiler)

    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)

}