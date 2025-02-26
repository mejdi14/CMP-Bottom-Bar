package org.mejdi14.aztopia


interface Platform {
    val name: String
}

expect fun getPlatform(): Platform