package expand.mejdi14.expandable.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape

@Composable
fun ExpandableBottomBar(
    bottomBarItems: List<BottomBarItem>,
    parentModifier: Modifier,
    onIconClick: (BottomBarItem) -> Unit,
    config: ExpandableBarConfig = ExpandableBarConfig(),
) {
    if (bottomBarItems.isEmpty()) return

    var selectedIndex by remember { mutableStateOf<Int?>(0) }
    val currentSelectedIndex = selectedIndex?.takeIf(bottomBarItems.indices::contains)
    val rows = expandableRows(bottomBarItems, config.rowCount)
    val selectedPosition = currentSelectedIndex?.let { index ->
        rows.withIndex().firstNotNullOfOrNull { (rowIndex, row) ->
            row.indexOfFirst { it.index == index }
                .takeIf { it >= 0 }
                ?.let { columnIndex -> rowIndex to columnIndex }
        }
    }

    BoxWithConstraints(
        modifier = parentModifier
            .width(config.width)
            .background(config.containerColor, config.shape)
            .padding(config.outerPadding),
    ) {
        val horizontalCellCount = rows.maxOf { it.size }
        val smallestCellWidth = maxWidth / horizontalCellCount
        val itemSize = expandableItemSize(config, smallestCellWidth)
        val indicatorSize = expandableIndicatorSize(config, itemSize)
        val selectedRow = selectedPosition?.first?.let(rows::get)
        val selectedCellWidth = selectedRow?.let { maxWidth / it.size } ?: 0.dp
        val targetIndicatorX = selectedPosition?.let { (_, columnIndex) ->
            selectedCellWidth * columnIndex + (selectedCellWidth - indicatorSize.width) / 2
        } ?: 0.dp
        val targetIndicatorY = selectedPosition?.let { (rowIndex, _) ->
            config.rowHeight * rowIndex + expandableIndicatorY(config, indicatorSize.height)
        } ?: 0.dp
        val indicatorX by animateDpAsState(
            targetValue = targetIndicatorX,
            label = "Expandable bottom bar indicator x",
        )
        val indicatorY by animateDpAsState(
            targetValue = targetIndicatorY,
            label = "Expandable bottom bar indicator y",
        )

        AnimatedVisibility(
            visible = selectedPosition != null,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut(),
        ) {
            Box(
                Modifier
                    .offset(indicatorX, indicatorY)
                    .size(indicatorSize.width, indicatorSize.height)
                    .background(config.indicator.color, indicatorSize.clipShape),
            )
        }

        Column {
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(config.rowHeight),
                ) {
                    row.forEach { indexedItem ->
                        val index = indexedItem.index
                        val item = indexedItem.value
                        val isSelected = currentSelectedIndex == index
                        val interactionSource = remember(item, index) {
                            MutableInteractionSource()
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .offset(y = expandableItemY(config, itemSize))
                                    .size(itemSize)
                                    .background(
                                        if (isSelected) {
                                            item.selectedBackgroundColor
                                        } else {
                                            item.backgroundColor
                                        },
                                        item.shape,
                                    )
                                    .semantics(mergeDescendants = true) {
                                        item.icon.contentDescription?.let {
                                            contentDescription = it
                                        }
                                        role = Role.Tab
                                        selected = isSelected
                                    }
                                    .clickable(
                                        enabled = item.interaction.enabled,
                                        interactionSource = interactionSource,
                                        indication = null,
                                        role = Role.Tab,
                                    ) {
                                        if (
                                            item.interaction.shouldDispatchClick(
                                                currentSelectedIndex,
                                                index,
                                            )
                                        ) {
                                            selectedIndex = item.interaction.nextSelectedIndex(
                                                currentSelectedIndex,
                                                index,
                                            )
                                            onIconClick(item)
                                        }
                                    },
                            ) {
                                val iconSize = (itemSize - item.icon.sizeReduction)
                                    .coerceAtLeast(1.dp)
                                val configuredTint = if (isSelected) {
                                    item.icon.selectedTint
                                } else {
                                    item.icon.tint
                                }
                                Icon(
                                    painter = painterResource(
                                        if (isSelected) {
                                            item.icon.selectedResource
                                        } else {
                                            item.icon.resource
                                        },
                                    ),
                                    contentDescription = item.icon.contentDescription,
                                    tint = if (configuredTint == Color.Unspecified) {
                                        config.iconColor
                                    } else {
                                        configuredTint
                                    },
                                    modifier = item.icon.modifier.size(iconSize),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private data class ExpandableIndicatorSize(
    val width: Dp,
    val height: Dp,
    val clipShape: androidx.compose.ui.graphics.Shape,
)

private fun expandableIndicatorSize(
    config: ExpandableBarConfig,
    itemSize: Dp,
): ExpandableIndicatorSize = when (config.indicator.shapeType) {
    BottomBarIndicatorShape.SQUARE -> ExpandableIndicatorSize(
        width = itemSize - config.indicator.padding,
        height = itemSize - config.indicator.padding,
        clipShape = config.indicator.shape,
    )

    BottomBarIndicatorShape.CIRCLE -> ExpandableIndicatorSize(
        width = itemSize - config.indicator.padding,
        height = itemSize - config.indicator.padding,
        clipShape = CircleShape,
    )

    BottomBarIndicatorShape.LINE -> ExpandableIndicatorSize(
        width = itemSize - config.indicator.padding,
        height = config.indicator.thickness,
        clipShape = config.indicator.shape,
    )

    BottomBarIndicatorShape.DOT -> ExpandableIndicatorSize(
        width = config.indicator.thickness,
        height = config.indicator.thickness,
        clipShape = CircleShape,
    )
}

private fun expandableItemSize(config: ExpandableBarConfig, cellWidth: Dp): Dp {
    val indicatorSpace = when (config.indicator.shapeType) {
        BottomBarIndicatorShape.LINE,
        BottomBarIndicatorShape.DOT,
        -> config.indicator.thickness + 3.dp

        else -> 0.dp
    }
    return minOf(config.itemSize, config.rowHeight - indicatorSpace, cellWidth)
        .coerceAtLeast(1.dp)
}

private fun expandableItemY(config: ExpandableBarConfig, itemSize: Dp): Dp {
    val indicatorSpace = when (config.indicator.shapeType) {
        BottomBarIndicatorShape.LINE,
        BottomBarIndicatorShape.DOT,
        -> config.indicator.thickness + 3.dp

        else -> 0.dp
    }
    val centeredOffset = (config.rowHeight - indicatorSpace - itemSize) / 2
    return if (
        indicatorSpace > 0.dp &&
        config.indicator.position == BottomBarIndicatorPosition.START
    ) {
        indicatorSpace + centeredOffset
    } else {
        centeredOffset
    }
}

private fun expandableIndicatorY(
    config: ExpandableBarConfig,
    indicatorHeight: Dp,
): Dp = when (config.indicator.shapeType) {
    BottomBarIndicatorShape.LINE,
    BottomBarIndicatorShape.DOT,
    -> if (config.indicator.position == BottomBarIndicatorPosition.START) {
        0.dp
    } else {
        config.rowHeight - indicatorHeight
    }

    else -> (config.rowHeight - indicatorHeight) / 2
}

internal fun <T> expandableRows(items: List<T>, requestedRowCount: Int): List<List<IndexedValue<T>>> {
    if (items.isEmpty()) return emptyList()

    val rowCount = requestedRowCount.coerceIn(1, items.size)
    val minimumRowSize = items.size / rowCount
    val rowsWithExtraItem = items.size % rowCount
    var itemIndex = 0
    return List(rowCount) { rowIndex ->
        val rowSize = minimumRowSize + if (rowIndex < rowsWithExtraItem) 1 else 0
        List(rowSize) {
            IndexedValue(itemIndex, items[itemIndex++])
        }
    }
}
