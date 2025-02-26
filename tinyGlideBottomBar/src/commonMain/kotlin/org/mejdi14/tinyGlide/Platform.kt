package org.mejdi14.tinyGlide

import org.mejdi14.core.Platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform