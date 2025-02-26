package org.mejdi14.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform