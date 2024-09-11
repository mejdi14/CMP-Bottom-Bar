package org.example.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform