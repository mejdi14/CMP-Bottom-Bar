package org.example.core.bottombar.data

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

data class BottomBarHoverText(
    val containerModifier: Modifier = Modifier,
    val textModifier: Modifier = Modifier,
    val textColor: Color = Color.White,
    val textStyle: TextStyle = TextStyle.Default
)
