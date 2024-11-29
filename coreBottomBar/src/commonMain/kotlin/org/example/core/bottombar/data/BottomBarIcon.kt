package org.example.core.bottombar.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

class BottomBarIcon(
    val iconDrawable: DrawableResource,
    val selectedIconDrawable: DrawableResource = iconDrawable,
    val iconTintColor: Color = Color.White,
    val selectedIconTint: Color = iconTintColor,
    val contentDescription: String = "bottom bar icon",
    val modifier: Modifier = Modifier,
    val sizeDifferenceComparedToParent: Dp = 10.dp
)