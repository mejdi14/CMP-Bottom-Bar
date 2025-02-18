plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

group = "io.github.mejdi14"

val versionName = project.findProperty("VERSION_NAME") as String? ?: "0.2.8"
version = versionName

subprojects {
    afterEvaluate {
        if (this.version.toString() == "unspecified") {
            this.version = versionName
        }
    }
}