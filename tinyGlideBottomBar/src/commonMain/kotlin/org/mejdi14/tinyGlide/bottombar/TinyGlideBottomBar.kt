package org.mejdi14.tinyGlide.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideState
import org.mejdi14.tinyGlide.data.isSelectedItem
import org.mejdi14.tinyGlide.data.rememberTinyGlideState
import org.mejdi14.tinyGlide.enum.TinyGlideOrientation
import org.mejdi14.tinyGlide.helper.handleHoverAction
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

@Composable
fun TinyGlideBottomBar(
    bottomBarItems: List<TinyGlideItem>,
    parentModifier: Modifier,
    tinyGlideActionListener: TinyGlideActionListener,
    state: TinyGlideState = rememberTinyGlideState(),
    orientation: TinyGlideOrientation = TinyGlideOrientation.HORIZONTAL,
) {
    val selectedIndex = state.selectedIndexState
    val expandedItem = state.expandedItemState
    val lazyListState = rememberLazyListState()
    val itemAnchors = remember(bottomBarItems, orientation) { mutableStateMapOf<Int, Offset>() }
    val containerPosition = remember { mutableStateOf(Offset.Zero) }
    val containerSize = remember { mutableStateOf(IntSize.Zero) }
    val hoverExitJob = remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val isHovering = remember { mutableStateOf(false) }
    val orientationModifier = when (orientation) {
        TinyGlideOrientation.HORIZONTAL -> Modifier.fillMaxWidth()
        TinyGlideOrientation.VERTICAL -> Modifier.fillMaxHeight()
    }
    DisposableEffect(state, tinyGlideActionListener) {
        state.actionListener = tinyGlideActionListener
        onDispose {
            if (state.actionListener === tinyGlideActionListener) {
                state.actionListener = null
            }
        }
    }

    Box(
        parentModifier
            .then(orientationModifier)
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->
                containerPosition.value = coordinates.positionInRoot()
                containerSize.value = coordinates.size
            },
    ) {
        val parentItem: @Composable (Int, TinyGlideItem) -> Unit = { index, item ->
            val animatedParentSize by animateDpAsState(
                targetValue = item.parentItemDynamicSize.value,
                animationSpec = tween(item.onSelectItemSizeChangeDurationMillis),
            )
            val spacingModifier = when (orientation) {
                TinyGlideOrientation.HORIZONTAL -> Modifier.width(item.itemSeparationSpace)
                TinyGlideOrientation.VERTICAL -> Modifier.height(item.itemSeparationSpace)
            }
            Spacer(spacingModifier)
            val interactionSource = remember(item) { MutableInteractionSource() }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(animatedParentSize)
                    .onGloballyPositioned { layoutCoordinates ->
                        val position = layoutCoordinates.positionInRoot()
                        val anchor = when (orientation) {
                            TinyGlideOrientation.HORIZONTAL -> Offset(
                                x = position.x + (layoutCoordinates.size.width / 2f),
                                y = position.y,
                            )

                            TinyGlideOrientation.VERTICAL -> Offset(
                                x = position.x,
                                y = position.y + (layoutCoordinates.size.height / 2f),
                            )
                        }
                        if (itemAnchors[index] != anchor) {
                            itemAnchors[index] = anchor
                        }
                        item.itemCoordinatesOffset = position
                    }
                    .background(
                        color = if (item.isSelectedItem(expandedItem.value)) {
                            item.selectedBackgroundColor
                        } else {
                            item.backgroundColor
                        },
                        shape = item.shape,
                    )
                    .hoverEffect { onHover ->
                        handleHoverAction(
                            isHovering = isHovering,
                            onHover = onHover,
                            item = item,
                            itemIndex = index,
                            state = state,
                            hoverExitJob = hoverExitJob,
                            scope = scope,
                            selectedItemAfterHover = {
                                selectedIndex.value?.let { selected ->
                                    bottomBarItems.getOrNull(selected)?.let { it to selected }
                                }
                            },
                        )
                    }
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                    ) {
                        if (item.interaction.shouldDispatchClick(selectedIndex.value, index)) {
                            val previouslyExpandedItem = expandedItem.value
                            val nextIndex =
                                item.interaction.nextSelectedIndex(selectedIndex.value, index)
                            val nextSelectedItem = nextIndex?.let(bottomBarItems::getOrNull)
                            item.onClick.onClick(item, index)
                            tinyGlideActionListener.onClick(item, index)
                            state.updateSelection(nextSelectedItem, nextIndex)
                            previouslyExpandedItem
                                ?.takeIf { it !== item }
                                ?.let { previousItem ->
                                    previousItem.parentItemDynamicSize.value = previousItem.size
                                }
                            if (nextIndex == null) {
                                item.parentItemDynamicSize.value = item.size
                                state.updateExpandedItem(null, null)
                            } else {
                                item.parentItemDynamicSize.value =
                                    item.size * item.onSelectItemSizeChangeFriction
                                state.updateExpandedItem(item, index)
                            }
                        }
                    },
            ) {
                TinyGlideIcon(
                    item = item,
                    selectedItem = expandedItem,
                    modifier = Modifier,
                    displaySize = animatedParentSize,
                )
            }
            Spacer(spacingModifier)
        }

        when (orientation) {
            TinyGlideOrientation.HORIZONTAL -> LazyRow(
                state = lazyListState,
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxWidth(),
                userScrollEnabled = true,
            ) {
                itemsIndexed(bottomBarItems) { index, item ->
                    parentItem(index, item)
                }
            }

            TinyGlideOrientation.VERTICAL -> LazyColumn(
                state = lazyListState,
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxHeight(),
                userScrollEnabled = true,
            ) {
                itemsIndexed(bottomBarItems) { index, item ->
                    parentItem(index, item)
                }
            }
        }

        val selectedParentIndex = expandedItem.value?.let { activeItem ->
            bottomBarItems.indexOfFirst { it === activeItem }.takeIf { it >= 0 }
        }
        val selectedParentAnchor = selectedParentIndex
            ?.let(itemAnchors::get)
            ?.let { rootAnchor ->
                Offset(
                    x = rootAnchor.x - containerPosition.value.x,
                    y = rootAnchor.y - containerPosition.value.y,
                )
            }
        SubItemsComposable(
            state = state,
            selectedParentAnchor = selectedParentAnchor,
            selectedItemAfterHover = selectedIndex.value?.let { selected ->
                bottomBarItems.getOrNull(selected)?.let { it to selected }
            },
            hoverExitJob = hoverExitJob,
            isHovering = isHovering,
            scope = scope,
            tinyGlideActionListener = tinyGlideActionListener,
            orientation = orientation,
            containerSize = containerSize.value,
        )
    }
}
