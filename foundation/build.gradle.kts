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
    dataBinding {
        isEnabled = true
    }
}

dependencies {

    implementation(Dependencies.androidX.core)
    implementation(Dependencies.androidX.material)
    implementation(Dependencies.androidX.constraintLayout)
    implementation(Dependencies.androidX.appCompat)

    implementation(Dependencies.okHttp.loggingInterceptor)
    implementation(Dependencies.okHttp)
    api(Dependencies.retrofit)
    implementation(Dependencies.retrofit.gsonConverter)
    implementation(Dependencies.test.junit)
    androidTestImplementation(Dependencies.androidTest.junit)

    // Hilt
    androidTestImplementation(Dependencies.androidTest.hilt)
    kaptAndroidTest(Dependencies.hilt.compiler)
    implementation(Dependencies.hilt.android)
    kapt(Dependencies.hilt.compiler)
}