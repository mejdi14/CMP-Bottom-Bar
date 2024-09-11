package org.example.project

class Greeting {
    private val platform = org.example.project.getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}