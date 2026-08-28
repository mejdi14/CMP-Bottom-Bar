package org.mejdi14.tinyGlide.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.isSelectedItem
import org.mejdi14.tinyGlide.helper.handleHoverAction
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener


@Composable
fun TinyGlideBottomBar(
    bottomBarItems: List<TinyGlideItem>,
    parentModifier: Modifier,
    tinyGlideActionListener: TinyGlideActionListener
) {
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    val lazyListState = rememberLazyListState()
    val selectedItem = remember { mutableStateOf<TinyGlideItem?>(null) }
    val itemAnchors = remember(bottomBarItems) { mutableStateMapOf<Int, Offset>() }
    val containerPosition = remember { mutableStateOf(Offset.Zero) }
    val hoverExitJob = remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val isHovering = remember { mutableStateOf(false) }
    Box(
        parentModifier
            .fillMaxWidth()
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->
                containerPosition.value = coordinates.positionInRoot()
            }
    ) {
        LazyRow(
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = true,
        ) {
            itemsIndexed(bottomBarItems) { index, item ->
                val animatedParentWidth by animateDpAsState(
                    targetValue = item.parentItemDynamicSize.value,
                    animationSpec = tween(durationMillis = item.onSelectItemSizeChangeDurationMillis)
                )
                Box(
                    Modifier.width(item.itemSeparationSpace)
                )
                val interactionSource = remember { MutableInteractionSource() }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(animatedParentWidth).align(Alignment.Center)
                        .onGloballyPositioned { layoutCoordinates ->
                            val position = layoutCoordinates.positionInRoot()
                            val anchor = Offset(
                                x = position.x + (layoutCoordinates.size.width / 2f),
                                y = position.y,
                            )
                            if (itemAnchors[index] != anchor) {
                                itemAnchors[index] = anchor
                            }
                            item.itemCoordinatesOffset = position
                        }
                        .background(
                            color = if (item.isSelectedItem(selectedItem.value))
                                item.selectedBackgroundColor
                            else item.backgroundColor,
                            shape = item.shape
                        )
                        .hoverEffect { onHover ->
                            handleHoverAction(
                                isHovering,
                                onHover,
                                item,
                                selectedItem,
                                hoverExitJob,
                                scope,
                                selectedItemAfterHover = {
                                    selectedIndex.value?.let(bottomBarItems::getOrNull)
                                },
                            )
                        }
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            if (item.interaction.shouldDispatchClick(selectedIndex.value, index)) {
                                val previouslyActiveItem = selectedItem.value
                                val nextIndex =
                                    item.interaction.nextSelectedIndex(selectedIndex.value, index)
                                item.onClick.onClick(item, index)
                                tinyGlideActionListener.onClick(item, index)
                                selectedIndex.value = nextIndex
                                previouslyActiveItem
                                    ?.takeIf { it !== item }
                                    ?.let { previousItem ->
                                        previousItem.parentItemDynamicSize.value = previousItem.size
                                    }
                                if (nextIndex == null) {
                                    item.parentItemDynamicSize.value = item.size
                                    selectedItem.value = null
                                } else {
                                    item.parentItemDynamicSize.value =
                                        item.size * item.onSelectItemSizeChangeFriction
                                    selectedItem.value = item
                                }
                            }
                        },
                ) {
                    TinyGlideIcon(
                        item = item,
                        selectedItem = selectedItem,
                        modifier = Modifier.align(Alignment.Center),
                        displaySize = animatedParentWidth,
                    )
                }
                Box(Modifier.width(item.itemSeparationSpace))
            }
        }
        val selectedParentIndex = selectedItem.value?.let { activeItem ->
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
            selectedItem,
            selectedIndex,
            selectedParentAnchor,
            selectedIndex.value?.let(bottomBarItems::getOrNull),
            hoverExitJob,
            isHovering,
            scope,
            tinyGlideActionListener
        )
    }
}
