package org.mejdi14.figma.bottombar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.mejdi14.core.bottombar.data.BottomBarItemGroup

@Composable
fun FigmaBottomBar(
    groups: List<BottomBarItemGroup<FigmaBarItem>>,
    modifier: Modifier = Modifier,
    config: FigmaBarConfig = FigmaBarConfig(),
    state: FigmaBarState = rememberFigmaBarState(),
    onItemClick: (FigmaBarItem, FigmaBarItemPosition) -> Unit = { _, _ -> },
    onItemHover: (FigmaBarItem, FigmaBarItemPosition, Boolean) -> Unit = { _, _, _ -> },
) {
    if (groups.isEmpty()) return

    val scrollState = rememberScrollState()

    Box(modifier = modifier.horizontalScroll(scrollState)) {
        Row(
            modifier = Modifier
                .shadow(config.elevation, config.shape)
                .background(config.containerColor, config.shape)
                .padding(config.contentPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var flatIndex = 0
            groups.forEachIndexed { groupIndex, group ->
                if (groupIndex > 0) {
                    Spacer(Modifier.width(config.groupSpacing))
                    Box(
                        Modifier
                            .width(config.dividerThickness)
                            .height(config.dividerHeight)
                            .background(config.dividerColor),
                    )
                    Spacer(Modifier.width(config.groupSpacing))
                }

                Row(horizontalArrangement = Arrangement.spacedBy(config.itemSpacing)) {
                    group.items.forEachIndexed { itemIndex, item ->
                        val position = FigmaBarItemPosition(
                            groupIndex = groupIndex,
                            itemIndex = itemIndex,
                            flatIndex = flatIndex,
                        )
                        FigmaBottomBarItem(
                            item = item,
                            position = position,
                            selected = normalizedFigmaBarIndex(
                                state.selectedIndex(groupIndex),
                                group.items.size,
                            ) == itemIndex,
                            config = config,
                            state = state,
                            onItemClick = onItemClick,
                            onItemHover = onItemHover,
                        )
                        flatIndex += 1
                    }
                }
            }
        }
    }
}

@Composable
private fun FigmaBottomBarItem(
    item: FigmaBarItem,
    position: FigmaBarItemPosition,
    selected: Boolean,
    config: FigmaBarConfig,
    state: FigmaBarState,
    onItemClick: (FigmaBarItem, FigmaBarItemPosition) -> Unit,
    onItemHover: (FigmaBarItem, FigmaBarItemPosition, Boolean) -> Unit,
) {
    val interactionSource = remember(item) { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    var dispatchedHover by remember(item) { mutableStateOf(false) }
    val itemWidth = config.itemSize + if (item.hasMenu) config.menuIndicatorSize else 0.dp
    val backgroundColor = when {
        selected -> config.selectedColor
        isHovered && item.interaction.enabled -> config.hoverColor
        else -> Color.Transparent
    }
    val fallbackTint = when {
        !item.interaction.enabled -> config.disabledContentColor
        selected -> config.selectedContentColor
        else -> config.contentColor
    }
    val configuredTint = if (selected) item.icon.selectedTint else item.icon.tint
    val iconTint = if (configuredTint == Color.Unspecified) fallbackTint else configuredTint

    LaunchedEffect(isHovered) {
        if (isHovered != dispatchedHover) {
            dispatchedHover = isHovered
            if (isHovered) {
                state.hoveredIndex = position.flatIndex
            } else if (state.hoveredIndex == position.flatIndex) {
                state.hoveredIndex = null
            }
            item.onHover.onHover(item, isHovered)
            onItemHover(item, position, isHovered)
        }
    }

    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(config.itemSize)
            .background(backgroundColor, config.itemShape)
            .hoverable(interactionSource, enabled = item.interaction.enabled)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = item.interaction.enabled,
            ) {
                val currentIndex = state.selectedIndex(position.groupIndex)
                if (item.interaction.shouldDispatchClick(currentIndex, position.itemIndex)) {
                    state.select(
                        groupIndex = position.groupIndex,
                        itemIndex = item.interaction.nextSelectedIndex(
                            currentIndex,
                            position.itemIndex,
                        ),
                    )
                    item.onClick.onClick(item, position.flatIndex)
                    onItemClick(item, position)
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(
                    if (selected) item.icon.selectedResource else item.icon.resource,
                ),
                contentDescription = item.icon.contentDescription,
                modifier = item.icon.modifier.size(config.iconSize),
                tint = iconTint,
            )
            if (item.hasMenu) {
                FigmaMenuIndicator(
                    color = iconTint,
                    modifier = Modifier.size(config.menuIndicatorSize),
                )
            }
        }
    }
}

@Composable
private fun FigmaMenuIndicator(
    color: Color,
    modifier: Modifier,
) {
    Canvas(modifier) {
        val strokeWidth = 1.dp.toPx()
        drawLine(
            color = color,
            start = Offset(size.width * 0.25f, size.height * 0.4f),
            end = Offset(size.width * 0.5f, size.height * 0.65f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.5f, size.height * 0.65f),
            end = Offset(size.width * 0.75f, size.height * 0.4f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

internal fun normalizedFigmaBarIndex(index: Int?, itemCount: Int): Int? =
    index?.takeIf { it in 0 until itemCount }
