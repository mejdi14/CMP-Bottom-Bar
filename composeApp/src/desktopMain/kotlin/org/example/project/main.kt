package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.App
import java.lang.reflect.Modifier


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-Bottom-Bar",
    ) {
        App()
    }
}