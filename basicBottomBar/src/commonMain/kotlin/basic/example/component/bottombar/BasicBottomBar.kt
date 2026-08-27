package basic.mejdi14.component.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarState
import basic.mejdi14.component.data.BasicItem
import basic.mejdi14.component.data.rememberBasicBarState

@Composable
fun BasicBottomBar(
    items: List<BasicItem>,
    modifier: Modifier = Modifier,
    config: BasicBarConfig = BasicBarConfig(),
    state: BasicBarState = rememberBasicBarState(),
    onItemClick: (item: BasicItem, index: Int?) -> Unit = { _, _ -> },
) {
    if (items.isEmpty()) return

    val selectedIndex = normalizedBasicBarIndex(state.selectedIndex, items.size)
    var hoveredIndex by remember { mutableStateOf<Int?>(null) }
    val indicatorOffset by animateDpAsState(
        targetValue = selectedIndex?.let {
            basicBarItemOffset(it, config.itemSize, config.itemSpacing)
        } ?: basicBarItemOffset(0, config.itemSize, config.itemSpacing),
        label = "Basic bottom bar indicator",
    )

    val handleHover: (Int, Boolean) -> Unit = { index, isHovered ->
        if (isHovered) {
            hoveredIndex = index
        } else if (hoveredIndex == index) {
            hoveredIndex = null
        }
        items[index].onHover.onHover(items[index], isHovered)
    }
    val handleClick: (Int) -> Unit = { index ->
        val item = items[index]
        val currentIndex = normalizedBasicBarIndex(state.selectedIndex, items.size)
        if (item.interaction.shouldDispatchClick(currentIndex, index)) {
            state.select(item.interaction.nextSelectedIndex(currentIndex, index))
            item.onClick.onClick(item, index)
            onItemClick(item, index)
        }
    }
    val handleAdditionalClick: (BasicItem) -> Unit = { item ->
        if (item.interaction.enabled) {
            item.onClick.onClick(item, null)
            onItemClick(item, null)
        }
    }

    if (config.position.isHorizontal) {
        HorizontalBasicBar(
            items = items,
            selectedIndex = selectedIndex,
            hoveredIndex = hoveredIndex,
            indicatorOffset = indicatorOffset,
            config = config,
            modifier = modifier,
            onHover = handleHover,
            onItemClick = handleClick,
            onAdditionalItemClick = handleAdditionalClick,
        )
    } else {
        VerticalBasicBar(
            items = items,
            selectedIndex = selectedIndex,
            hoveredIndex = hoveredIndex,
            indicatorOffset = indicatorOffset,
            config = config,
            modifier = modifier,
            onHover = handleHover,
            onItemClick = handleClick,
            onAdditionalItemClick = handleAdditionalClick,
        )
    }
}
