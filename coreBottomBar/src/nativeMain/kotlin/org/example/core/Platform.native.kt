package org.example.core

actual fun getPlatform(): Platform = object : Platform {
    override val name: String = "Native"
}