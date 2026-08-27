package basic.mejdi14.component.bottombar.hover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import basic.mejdi14.component.bottombar.basicBarItemOffset
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarPosition
import basic.mejdi14.component.data.BasicItem

@Composable
internal fun BasicBarHoverLabel(
    item: BasicItem?,
    index: Int?,
    config: BasicBarConfig,
) {
    val text = item?.hoverText?.takeIf { it.isNotBlank() } ?: return
    val hoveredIndex = index ?: return
    val style = config.hoverTextStyle
    val density = LocalDensity.current
    var labelWidth by remember { mutableStateOf(0.dp) }
    val itemOffset = basicBarItemOffset(hoveredIndex, config.itemSize, config.itemSpacing)
    val barCrossAxisSize = config.itemSize + (config.contentPadding * 2)
    val xOffset = when (config.position) {
        BasicBarPosition.HorizontalBottom,
        BasicBarPosition.HorizontalTop,
        -> config.contentPadding + itemOffset + (config.itemSize / 2) - (labelWidth / 2)

        BasicBarPosition.VerticalLeft -> barCrossAxisSize + style.gap
        BasicBarPosition.VerticalRight -> -labelWidth - style.gap
    }
    val yOffset = when (config.position) {
        BasicBarPosition.HorizontalBottom -> -style.height - style.gap
        BasicBarPosition.HorizontalTop -> barCrossAxisSize + style.gap
        BasicBarPosition.VerticalLeft,
        BasicBarPosition.VerticalRight,
        -> config.contentPadding + itemOffset + (config.itemSize / 2) - (style.height / 2)
    }

    Box(
        modifier = Modifier
            .offset(x = xOffset, y = yOffset)
            .height(style.height)
            .then(style.containerModifier)
            .onGloballyPositioned { coordinates ->
                labelWidth = with(density) { coordinates.size.width.toDp() }
            }
            .clip(style.shape)
            .background(style.containerColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(style.contentPadding).then(style.textModifier),
            color = style.contentColor,
            style = style.textStyle,
        )
    }
}
