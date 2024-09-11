package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-Bottom-Bar",
    ) {
        App()
    }
}