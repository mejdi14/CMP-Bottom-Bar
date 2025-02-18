package org.example.gooey

import androidx.compose.ui.Modifier


actual fun Modifier.platformBlur(
    blurRadius: Float,
    alphaMultiplier: Float,
    alphaOffset: Float
): Modifier = Modifier
