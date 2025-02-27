package org.mejdi14.core.bottombar.indicator

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class SelectedIndicatorConfig(
    val color: Color = Color.Blue,
    val shapeType: BasicIndicatorShapeType = BasicIndicatorShapeType.Square,
    val positionType: PositionType = PositionType.Bottom,
    val size: Dp = 60.dp,
    val thickness: Dp = 6.dp,
    val padding: Dp = 10.dp,
    val shape: Shape = RoundedCornerShape(10.dp)
)