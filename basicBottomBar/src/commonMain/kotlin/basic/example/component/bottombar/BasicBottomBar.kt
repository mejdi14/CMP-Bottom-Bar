package basic.mejdi14.component.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarPosition
import basic.mejdi14.component.data.BasicItem
import org.mejdi14.core.bottombar.data.BottomBarItem

@Composable
fun BasicBottomBar(
    bottomBarItems: List<BasicItem>,
    basicBarConfig: BasicBarConfig,
    parentModifier: Modifier,
    onIconClick: (BottomBarItem) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val hoverSelectedIndex = remember { mutableStateOf(0) }

    val lazyListState = rememberLazyListState()

    var parentWidth = remember { mutableStateOf(0.dp) }
    var parentHeight = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val isHovered = remember { mutableStateOf(false) }
    val animatedOffset = animateDpAsState(
        targetValue = (selectedIndex.value * basicBarConfig.itemSize.value).dp
    )
    var spaceBetween = remember { mutableStateOf(0.dp) }
    when(basicBarConfig.basicBarPosition){
        BasicBarPosition.HORIZONTAL_BOTTOM, BasicBarPosition.HORIZONTAL_TOP -> {
            HorizontalBasicBar(
                parentModifier,
                spaceBetween,
                hoverSelectedIndex,
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
