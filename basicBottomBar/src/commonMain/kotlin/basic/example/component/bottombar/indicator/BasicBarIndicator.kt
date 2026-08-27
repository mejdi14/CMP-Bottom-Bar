package basic.mejdi14.component.bottombar.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basic.mejdi14.component.data.BasicBarPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape

@Composable
internal fun BasicBarIndicator(
    config: BottomBarIndicatorConfig,
    offset: Dp,
    barPosition: BasicBarPosition,
    itemSize: Dp,
) {
    val isHorizontal = barPosition.isHorizontal
    val indicatorLayout = basicBarIndicatorLayout(config, itemSize)
    val shapeModifier = when (config.shapeType) {
        BottomBarIndicatorShape.SQUARE -> Modifier
            .size(itemSize)
            .background(color = config.color, shape = config.shape)

        BottomBarIndicatorShape.LINE -> if (isHorizontal) {
            Modifier.width(indicatorLayout.itemSize).height(config.thickness)
        } else {
            Modifier.height(indicatorLayout.itemSize).width(config.thickness)
        }.background(color = config.color, shape = config.shape)

        BottomBarIndicatorShape.CIRCLE -> Modifier
            .size(itemSize)
            .background(color = config.color, shape = CircleShape)

        BottomBarIndicatorShape.DOT -> Modifier
            .size(config.thickness)
            .background(color = config.color, shape = CircleShape)
    }
    val mainAxisOffset = offset + when (config.shapeType) {
        BottomBarIndicatorShape.LINE -> (itemSize - indicatorLayout.itemSize) / 2
        BottomBarIndicatorShape.DOT -> (itemSize - config.thickness) / 2
        else -> 0.dp
    }

    Box(
        modifier = Modifier
            .offset(
                x = if (isHorizontal) mainAxisOffset else indicatorLayout.indicatorCrossAxisOffset,
                y = if (isHorizontal) indicatorLayout.indicatorCrossAxisOffset else mainAxisOffset,
            )
            .then(shapeModifier)
    )
}

internal data class BasicBarIndicatorLayout(
    val slotSize: Dp,
    val itemSize: Dp,
    val itemCrossAxisOffset: Dp,
    val indicatorCrossAxisOffset: Dp,
)

internal fun basicBarIndicatorLayout(
    config: BottomBarIndicatorConfig,
    itemSize: Dp,
): BasicBarIndicatorLayout {
    val isOutsideItem = config.shapeType == BottomBarIndicatorShape.LINE ||
        config.shapeType == BottomBarIndicatorShape.DOT
    if (!isOutsideItem) {
        return BasicBarIndicatorLayout(
            slotSize = itemSize,
            itemSize = itemSize,
            itemCrossAxisOffset = 0.dp,
            indicatorCrossAxisOffset = 0.dp,
        )
    }

    val indicatorSpace = config.thickness + config.padding
    val reducedItemSize = itemSize - indicatorSpace
    return BasicBarIndicatorLayout(
        slotSize = itemSize,
        itemSize = reducedItemSize,
        itemCrossAxisOffset = if (config.position == BottomBarIndicatorPosition.START) {
            indicatorSpace
        } else {
            0.dp
        },
        indicatorCrossAxisOffset = if (config.position == BottomBarIndicatorPosition.START) {
            0.dp
        } else {
            reducedItemSize + config.padding
        },
    )
}
