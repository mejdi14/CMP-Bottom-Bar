package org.mejdi14.tinyGlide


interface Platform {
    val name: String
}

expect fun getPlatform(): Platform