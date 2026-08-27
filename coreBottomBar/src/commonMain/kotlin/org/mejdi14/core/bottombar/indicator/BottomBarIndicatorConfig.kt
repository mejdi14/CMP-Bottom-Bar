package org.mejdi14.core.bottombar.indicator

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class BottomBarIndicatorShape {
    SQUARE,
    LINE,
    CIRCLE,
    DOT,
}

enum class BottomBarIndicatorPosition {
    START,
    END,
}

data class BottomBarIndicatorConfig(
    val color: Color = Color.Blue,
    val shapeType: BottomBarIndicatorShape = BottomBarIndicatorShape.SQUARE,
    val position: BottomBarIndicatorPosition = BottomBarIndicatorPosition.END,
    val thickness: Dp = 6.dp,
    val padding: Dp = 10.dp,
    val shape: Shape = RoundedCornerShape(10.dp),
)
