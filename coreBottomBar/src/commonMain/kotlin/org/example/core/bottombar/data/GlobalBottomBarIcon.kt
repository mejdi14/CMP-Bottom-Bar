package org.example.core.bottombar.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class GlobalBottomBarIcon (
    val iconTintColor: Color = Color.White,
    val selectedIconTintColor: Color = iconTintColor,
    val contentDescription: String = "bottom bar icon",
    val modifier: Modifier = Modifier,
    val sizeDifferenceComparedToParent: Dp = 5.dp
)