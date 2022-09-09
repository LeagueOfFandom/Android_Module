plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

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
    dataBinding {
        isEnabled = true
    }
}

dependencies {

    implementation(project(":foundation"))

    api(Dependencies.kotlin.coroutine)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.material)
    implementation(Dependencies.androidX.constraintLayout)
    implementation(Dependencies.androidX.appCompat)
    implementation(Dependencies.glide.android)
    implementation(Dependencies.glide.annotationProcessor)
    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.material)
    implementation(Dependencies.androidX.constraintLayout)
    implementation(Dependencies.androidX.appCompat)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.androidX.junit)
    androidTestImplementation(Dependencies.AndroidTest.espressoCore)
}