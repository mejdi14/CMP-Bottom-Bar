package org.example.core.bottombar.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class BottomBarHoverText(
    val containerModifier: Modifier = Modifier,
    val textModifier: Modifier = Modifier,
    val textColor: Color = Color.White,
    val textStyle: TextStyle = TextStyle.Default
)
