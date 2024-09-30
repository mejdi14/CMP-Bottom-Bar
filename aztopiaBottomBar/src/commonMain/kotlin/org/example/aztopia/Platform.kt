package org.example.aztopia

import org.example.core.Platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform