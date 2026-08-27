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
    val shapeModifier = when (config.shapeType) {
        BottomBarIndicatorShape.SQUARE -> Modifier
            .size(itemSize)
            .background(color = config.color, shape = config.shape)

        BottomBarIndicatorShape.LINE -> if (isHorizontal) {
            Modifier.width(itemSize).height(config.thickness)
        } else {
            Modifier.height(itemSize).width(config.thickness)
        }.background(color = config.color, shape = config.shape)

        BottomBarIndicatorShape.CIRCLE -> Modifier
            .size(itemSize)
            .background(color = config.color, shape = CircleShape)

        BottomBarIndicatorShape.DOT -> Modifier
            .size(config.thickness)
            .background(color = config.color, shape = CircleShape)
    }
    val mainAxisOffset = offset + if (config.shapeType == BottomBarIndicatorShape.DOT) {
        (itemSize - config.thickness) / 2
    } else {
        0.dp
    }
    val crossAxisOffset = when {
        config.shapeType != BottomBarIndicatorShape.LINE &&
            config.shapeType != BottomBarIndicatorShape.DOT -> 0.dp

        config.position == BottomBarIndicatorPosition.START -> config.padding
        else -> itemSize - config.thickness - config.padding
    }

    Box(
        modifier = Modifier
            .offset(
                x = if (isHorizontal) mainAxisOffset else crossAxisOffset,
                y = if (isHorizontal) crossAxisOffset else mainAxisOffset,
            )
            .then(shapeModifier)
    )
}
