object Dependencies {
    val kotlin = Kotlin
    val hilt = Hilt
    val androidX = AndroidX
    val retrofit = Retrofit()
    val okHttp = OkHttp()
    val test = Test
    val androidTest = AndroidTest
    val glide = Glide
    val google = Google

    object Kotlin {
        val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCore}" }
    }

    object Hilt {
        val android by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val compiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    }

    object AndroidX {
        val paging = Paging

        val core by lazy { "androidx.core:core-ktx:1.8.0" }
        val appCompat by lazy { "androidx.appcompat:appcompat:1.4.2" }
        val material by lazy { "com.google.android.material:material:1.6.1" }
        val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:2.1.4" }
        val activity by lazy { "androidx.activity:activity-ktx:1.5.0" }
        val fragment by lazy { "androidx.fragment:fragment-ktx:1.5.0" }

        object Paging {
            val runtime by lazy { "androidx.paging:paging-runtime-ktx:${Versions.paging}" }
            val common by lazy { "androidx.paging:paging-common:${Versions.paging}" }
        }

        val junit by lazy { "androidx.test.ext:junit:1.1.3" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:3.4.0" }

        val lifecycle = Lifecycle
        object Lifecycle {
            val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
            val liveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
        }

        val navigation = Navigation

        object Navigation {
            val fragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
            val ui by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
        }
    }

    class Retrofit(
        private val name: String = "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
    ) : CharSequence by name {
        val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
        val scalarsConverter by lazy { "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}" }

        override fun toString() = name
    }

    class OkHttp(
        private val name: String = "com.squareup.okhttp3:okhttp:${Versions.okHttp}",
    ) : CharSequence by name {
        val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}" }

        override fun toString() = name
    }

    object Test {
        val junit by lazy { "junit:junit:4.13.2" }
        val robolectric by lazy { "org.robolectric:robolectric:4.6.1" }
        val archCore by lazy { "androidx.arch.core:core-testing:2.1.0" }
        val mockito by lazy { "org.mockito:mockito-core:3.8.0" }
        val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1" }
    }

    object AndroidTest {
        val runner by lazy { "androidx.test:runner:1.4.0" }
        val rules by lazy { "androidx.test:rules:1.4.0" }
        val archCore by lazy { "androidx.arch.core:core-testing:2.1.0" }
        val junit by lazy { "androidx.test.ext:junit-ktx:1.1.3" }
        val espressoCore by lazy { "androidx.test.espresso:espresso-core:3.4.0" }
        val espressoContribute by lazy { "androidx.test.espresso:espresso-contrib:3.4.0" }
        val mockito by lazy { "org.mockito:mockito-android:3.8.0" }
        val hilt by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }
        val fragment by lazy { "androidx.fragment:fragment-testing:1.4.1" }
    }

    object Glide {
        val android by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
        val annotationProcessor by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
    }

    object Google {
        val auth by lazy { "com.google.android.gms:play-services-auth:${Versions.googleServiceAuth}" }
    }
}