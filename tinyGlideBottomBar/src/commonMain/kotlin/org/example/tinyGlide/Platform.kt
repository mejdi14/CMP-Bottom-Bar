package org.example.tinyGlide

import org.example.core.Platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform