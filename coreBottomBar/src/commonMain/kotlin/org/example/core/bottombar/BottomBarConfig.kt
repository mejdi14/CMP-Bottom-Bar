package org.example.core.bottombar

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class BottomBarConfig(
    val shapeType: ShapeType = ShapeType.Square,
    val positionType: PositionType = PositionType.Bottom,
    val size: Dp = 60.dp,
    val thickness: Dp = 3.dp,
    val padding: Dp = 10.dp,
)