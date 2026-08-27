import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.mavenPublish)
    signing
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.resources)
            api(project(":coreBottomBar"))
        }
    }
}

android {
    namespace = "org.mejdi14.aztopia"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

if ((project.findProperty("RELEASE_SIGNING_ENABLED")?.toString() ?: "false").toBoolean()) {
    signing {
        useGpgCmd()
        sign(publishing.publications)
    }
}

mavenPublishing {
    coordinates(
        artifactId = "cmp-bottombar-aztopia",
    )

    pom {
        name.set("CMP Bottom Bar - Aztopia")
        description.set("Aztopia library for the CMP Bottom Bar multi-platform library.")
        url.set("https://github.com/mejdi14/CMP-Bottom-Bar")

        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }
        scm {
            url.set("https://github.com/mejdi14/CMP-Bottom-Bar")
            connection.set("scm:git:git://github.com/mejdi14/CMP-Bottom-Bar.git")
            developerConnection.set("scm:git:ssh://git@github.com/mejdi14/CMP-Bottom-Bar.git")
        }
        developers {
            developer {
                id.set("mejdi14")
                name.set("mejdi hafiene")
                url.set("https://github.com/mejdi14/")
                email.set("mejdihafiane@gmail.com")
            }
        }
    }
}
