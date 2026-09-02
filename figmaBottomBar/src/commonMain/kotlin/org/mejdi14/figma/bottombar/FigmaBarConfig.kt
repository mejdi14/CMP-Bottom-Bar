package org.mejdi14.figma.bottombar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FigmaBarConfig(
    val containerColor: Color = Color(0xFF2C2C2C),
    val contentColor: Color = Color(0xFFF5F5F5),
    val selectedColor: Color = Color(0xFF0D99FF),
    val selectedContentColor: Color = Color.White,
    val hoverColor: Color = Color(0xFF3D3D3D),
    val disabledContentColor: Color = Color(0xFF8C8C8C),
    val dividerColor: Color = Color(0xFF444444),
    val shape: Shape = RoundedCornerShape(12.dp),
    val itemShape: Shape = RoundedCornerShape(6.dp),
    val itemSize: Dp = 38.dp,
    val iconSize: Dp = 20.dp,
    val menuIndicatorSize: Dp = 8.dp,
    val itemSpacing: Dp = 2.dp,
    val groupSpacing: Dp = 8.dp,
    val contentPadding: Dp = 6.dp,
    val dividerHeight: Dp = 26.dp,
    val dividerThickness: Dp = 1.dp,
    val elevation: Dp = 10.dp,
) {
    init {
        require(itemSize > 0.dp) { "itemSize must be greater than zero." }
        require(iconSize > 0.dp) { "iconSize must be greater than zero." }
        require(iconSize <= itemSize) { "iconSize must not exceed itemSize." }
        require(menuIndicatorSize > 0.dp) { "menuIndicatorSize must be greater than zero." }
        require(itemSpacing >= 0.dp) { "itemSpacing must not be negative." }
        require(groupSpacing >= 0.dp) { "groupSpacing must not be negative." }
        require(contentPadding >= 0.dp) { "contentPadding must not be negative." }
        require(dividerHeight > 0.dp) { "dividerHeight must be greater than zero." }
        require(dividerThickness > 0.dp) { "dividerThickness must be greater than zero." }
        require(elevation >= 0.dp) { "elevation must not be negative." }
    }

    companion object {
        fun light() = FigmaBarConfig(
            containerColor = Color(0xFFFAFAFA),
            contentColor = Color(0xFF1E1E1E),
            selectedColor = Color(0xFF0D99FF),
            selectedContentColor = Color.White,
            hoverColor = Color(0xFFE8E8E8),
            disabledContentColor = Color(0xFF9B9B9B),
            dividerColor = Color(0xFFE5E5E5),
        )
    }
}
