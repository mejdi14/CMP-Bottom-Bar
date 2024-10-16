package basic.example.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basic.example.component.data.BasicBarPosition
import basic.example.component.data.BasicItem

@Composable
internal fun HoverDescriptionTextComposable(
    spaceBetween: Dp,
    selectedIndex: MutableState<Int>,
    itemWidth: Dp,
    bottomBarItems: List<BasicItem>,
    isHovered: MutableState<Boolean>,
    basicBarPosition: BasicBarPosition
) {
    var parentWidth by remember { mutableStateOf(0.dp) }
    var parentHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    if (isHovered.value)
        Box(modifier = Modifier.height(30.dp)
            .onGloballyPositioned { layoutCoordinates ->
                val widthPx = layoutCoordinates.size.width
                val heightPx = layoutCoordinates.size.height
                parentWidth = with(density) { widthPx.toDp() }
                parentHeight = with(density) { heightPx.toDp() }
            }
            .offset(
                x = when (basicBarPosition) {
                    BasicBarPosition.HORIZONTAL_TOP, BasicBarPosition.HORIZONTAL_BOTTOM -> {
                        ((selectedIndex.value * itemWidth.value).dp + (spaceBetween * (selectedIndex.value + 1))) + (itemWidth / 2) - (parentWidth / 2)
                    }

                    else -> 0.dp
                },
                y = when (basicBarPosition) {
                    BasicBarPosition.VERTICAL_LEFT, BasicBarPosition.VERTICAL_RIGHT -> {
                        ((selectedIndex.value * itemWidth.value).dp + (spaceBetween * (selectedIndex.value + 1))) + (itemWidth / 2) - (parentHeight / 2)
                    }

                    else -> 0.dp
                }
            )
            .clip(RoundedCornerShape(6.dp)).background(color = Color.Black)
        ) {
            Text(
                bottomBarItems.get(selectedIndex.value).hoverText,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center).padding(4.dp)
            )
        }
}