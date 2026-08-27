package expand.mejdi14.expandable.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape

@Composable
fun CustomBottomBar(
    config: BottomBarIndicatorConfig,
    color: Color = Color.Blue,
    animatedOffset: State<Dp>,
    spaceBetween: Dp,
    selectedIndex: MutableState<Int>,
    itemSize: Dp = 50.dp,
    rowHeight: Dp = 60.dp,
) {

    val shapeModifier = when (config.shapeType) {
        BottomBarIndicatorShape.SQUARE -> Modifier.size(
            itemSize - config.padding,
            itemSize - config.padding,
        )

        BottomBarIndicatorShape.LINE -> Modifier.width(itemSize - config.padding).height(config.thickness)
        BottomBarIndicatorShape.CIRCLE -> Modifier.size(itemSize - config.padding).clip(CircleShape)
        BottomBarIndicatorShape.DOT -> Modifier.size(config.thickness).clip(CircleShape)
    }

    val indicatorHeight = when (config.shapeType) {
        BottomBarIndicatorShape.LINE,
        BottomBarIndicatorShape.DOT -> config.thickness
        else -> itemSize - config.padding
    }
    val verticalOffset = when (config.shapeType) {
        BottomBarIndicatorShape.LINE,
        BottomBarIndicatorShape.DOT -> if (config.position == BottomBarIndicatorPosition.END) {
            rowHeight - indicatorHeight
        } else {
            0.dp
        }
        else -> (rowHeight - indicatorHeight) / 2
    }

    Box(
        modifier = Modifier
            .offset(
                x = (animatedOffset.value + (spaceBetween * (selectedIndex.value + 1))),
                y = verticalOffset,
            )
            .then(shapeModifier)
            .background(color, RoundedCornerShape(10.dp))
    )
}
