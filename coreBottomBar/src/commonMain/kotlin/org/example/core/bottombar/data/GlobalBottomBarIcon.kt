package org.example.core.bottombar.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

data class GlobalBottomBarIcon (
    val iconTintColor: Color = Color.White,
    val selectedIconTint: Color = iconTintColor,
    val contentDescription: String = "bottom bar icon",
    val modifier: Modifier = Modifier,
    val sizeDifferenceComparedToParent: Dp = 5.dp
)