package org.mejdi14.gooey

import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { }

actual fun Modifier.platformBlur(
    blurRadius: Float,
    alphaMultiplier: Float,
    alphaOffset: Float
): Modifier = Modifier