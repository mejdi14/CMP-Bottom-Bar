plugins {
    // Keep shared plugins on the root classpath so subprojects load each plugin once.
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.mavenPublish) apply false
}

val libraryGroup = "io.github.mejdi14"
val libraryVersion = providers.gradleProperty("VERSION_NAME").getOrElse("0.4.4-SNAPSHOT")

group = libraryGroup
version = libraryVersion

subprojects {
    // The demo app keeps its generated resource package independent of Maven coordinates.
    if (name.endsWith("BottomBar")) {
        group = libraryGroup
    }
    version = libraryVersion
}
