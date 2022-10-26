pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url = "https://jitpack.io" )
    }
}
rootProject.name = "LoF"
include(":app")
include(":domain")
include(":login")
include(":common-ui")
include(":select-team")
include(":home")
include(":community")
include(":setting")
include(":info")
include(":match")
include(":core-model")
include(":core-network")
include(":core-data")
