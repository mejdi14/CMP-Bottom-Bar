package basic.example.component.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import basic.example.component.data.BasicBarConfig
import basic.example.component.data.BasicBarPosition
import basic.example.component.data.BasicItem
import org.example.core.bottombar.data.BottomBarItem

@Composable
fun BasicBottomBar(
    bottomBarItems: List<BasicItem>,
    basicBarConfig: BasicBarConfig,
    parentModifier: Modifier,
    onIconClick: (BottomBarItem) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val hoverSelectedIndex = remember { mutableStateOf(0) }
    val itemWidth = 50.dp

    val lazyListState = rememberLazyListState()

    var parentWidth = remember { mutableStateOf(0.dp) }
    var parentHeight = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val bottomBarWidth = itemWidth * bottomBarItems.size + 10.dp
    val isHovered = remember { mutableStateOf(false) }
    val animatedOffset = animateDpAsState(
        targetValue = (selectedIndex.value * itemWidth.value).dp
    )
    var spaceBetween = remember { mutableStateOf(0.dp) }
    when(basicBarConfig.basicBarPosition){
        BasicBarPosition.HORIZONTAL_BOTTOM, BasicBarPosition.HORIZONTAL_TOP -> {
            HorizontalBasicBar(
                parentModifier,
                spaceBetween,
                hoverSelectedIndex,
                itemWidth,
                bottomBarItems,
                isHovered,
                parentWidth,
                density,
                animatedOffset,
                selectedIndex,
                basicBarConfig,
                lazyListState,
                onIconClick
            )
        }
        BasicBarPosition.VERTICAL_LEFT, BasicBarPosition.VERTICAL_RIGHT -> {
            VerticalBasicBar(
                parentModifier,
                spaceBetween,
                hoverSelectedIndex,
                itemWidth,
                bottomBarItems,
                isHovered,
                parentHeight,
                density,
                animatedOffset,
                selectedIndex,
                basicBarConfig,
                lazyListState,
                onIconClick
            )

        }
    }
}
