package basic.mejdi14.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import basic.mejdi14.component.bottombar.additional.BasicAdditionalItem
import basic.mejdi14.component.bottombar.hover.BasicBarHoverLabel
import basic.mejdi14.component.bottombar.indicator.BasicBarIndicator
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicItem

@Composable
internal fun HorizontalBasicBar(
    items: List<BasicItem>,
    selectedIndex: Int?,
    hoveredIndex: Int?,
    indicatorOffset: Dp,
    config: BasicBarConfig,
    modifier: Modifier,
    onHover: (Int, Boolean) -> Unit,
    onItemClick: (Int) -> Unit,
    onAdditionalItemClick: (BasicItem) -> Unit,
) {
    val additionalItems = config.additionalItems
    val hasAdditionalItems = additionalItems?.startItem != null || additionalItems?.endItem != null

    Row(
        modifier = modifier.padding(config.outerPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (hasAdditionalItems) {
            BasicAdditionalItem(
                item = additionalItems.startItem,
                config = config,
                onClick = onAdditionalItemClick,
            )
            Spacer(Modifier.width(config.itemSpacing))
        }

        Box {
            HorizontalBarSurface(
                items = items,
                selectedIndex = selectedIndex,
                hoveredIndex = hoveredIndex,
                indicatorOffset = indicatorOffset,
                config = config,
                onHover = onHover,
                onItemClick = onItemClick,
            )
            BasicBarHoverLabel(
                item = hoveredIndex?.let(items::getOrNull),
                index = hoveredIndex,
                config = config,
            )
        }

        if (hasAdditionalItems) {
            Spacer(Modifier.width(config.itemSpacing))
            BasicAdditionalItem(
                item = additionalItems.endItem,
                config = config,
                onClick = onAdditionalItemClick,
            )
        }
    }
}

@Composable
private fun HorizontalBarSurface(
    items: List<BasicItem>,
    selectedIndex: Int?,
    hoveredIndex: Int?,
    indicatorOffset: Dp,
    config: BasicBarConfig,
    onHover: (Int, Boolean) -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val stripWidth = basicBarStripSize(items.size, config.itemSize, config.itemSpacing)
    Box(
        modifier = Modifier
            .background(color = config.containerColor, shape = config.shape)
            .padding(config.contentPadding),
    ) {
        Box(Modifier.width(stripWidth).height(config.itemSize)) {
            Row(horizontalArrangement = Arrangement.spacedBy(config.itemSpacing)) {
                items.forEachIndexed { index, item ->
                    BasicBarItemBackground(
                        item = item,
                        isSelected = selectedIndex == index,
                        isHovered = hoveredIndex == index && selectedIndex != index,
                        config = config,
                    )
                }
            }
            if (selectedIndex != null) {
                BasicBarIndicator(
                    config = config.indicator,
                    offset = indicatorOffset,
                    barPosition = config.position,
                    itemSize = config.itemSize,
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(config.itemSpacing)) {
                items.forEachIndexed { index, item ->
                    BasicBarItemForeground(
                        item = item,
                        index = index,
                        isSelected = selectedIndex == index,
                        config = config,
                        onHover = onHover,
                        onClick = onItemClick,
                    )
                }
            }
        }
    }
}
