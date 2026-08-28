package org.mejdi14.tinyGlide.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mejdi14.tinyGlide.animation.getEnterTransition
import org.mejdi14.tinyGlide.animation.getExitTransition
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideItemPosition
import org.mejdi14.tinyGlide.data.TinyGlideState
import org.mejdi14.tinyGlide.enum.AnimationType
import org.mejdi14.tinyGlide.enum.TinyGlideOrientation
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SubItemsComposable(
    state: TinyGlideState,
    selectedParentAnchor: Offset?,
    selectedItemAfterHover: Pair<TinyGlideItem, Int>?,
    hoverExitJob: MutableState<Job?>,
    isHovering: MutableState<Boolean>,
    scope: CoroutineScope,
    tinyGlideActionListener: TinyGlideActionListener,
    orientation: TinyGlideOrientation,
    containerSize: IntSize,
    animationType: AnimationType = AnimationType.SCALE,
) {
    val currentItem = state.expandedItem
    val subItems = currentItem?.subTinyGlideItems.orEmpty()
    val density = LocalDensity.current
    val groupWidth = when (orientation) {
        TinyGlideOrientation.HORIZONTAL -> subItems.fold(0.dp) { width, item ->
            width + item.size + (item.itemSeparationSpace * 2)
        }

        TinyGlideOrientation.VERTICAL -> subItems.maxOfOrNull { it.size } ?: 0.dp
    }
    val groupHeight = when (orientation) {
        TinyGlideOrientation.HORIZONTAL -> subItems.maxOfOrNull { it.size } ?: 0.dp
        TinyGlideOrientation.VERTICAL -> subItems.fold(0.dp) { height, item ->
            height + item.size + (item.itemSeparationSpace * 2)
        }
    }
    val parentAnchorX = with(density) { (selectedParentAnchor?.x ?: 0f).toDp() }
    val parentAnchorY = with(density) { (selectedParentAnchor?.y ?: 0f).toDp() }
    val containerWidth = with(density) { containerSize.width.toDp() }
    val containerHeight = with(density) { containerSize.height.toDp() }
    val edgePadding = 5.dp
    val groupOffsetX = when (orientation) {
        TinyGlideOrientation.HORIZONTAL -> keepGroupInsideContainer(
            preferredOffset = parentAnchorX - (groupWidth / 2),
            groupSize = groupWidth,
            containerSize = containerWidth,
            edgePadding = edgePadding,
        )

        TinyGlideOrientation.VERTICAL ->
            parentAnchorX - (currentItem?.parentAndSubVerticalSeparationSpace ?: 0.dp) - groupWidth
    }
    val groupOffsetY = when (orientation) {
        TinyGlideOrientation.HORIZONTAL ->
            parentAnchorY - (currentItem?.parentAndSubVerticalSeparationSpace ?: 0.dp) - groupHeight

        TinyGlideOrientation.VERTICAL -> keepGroupInsideContainer(
            preferredOffset = parentAnchorY - (groupHeight / 2),
            groupSize = groupHeight,
            containerSize = containerHeight,
            edgePadding = edgePadding,
        )
    }

    Box(
        modifier = if (currentItem != null && selectedParentAnchor != null) {
            Modifier
                .offset(
                    x = groupOffsetX,
                    y = groupOffsetY,
                )
                .hoverEffect { onHover ->
                    isHovering.value = onHover
                    if (onHover) {
                        hoverExitJob.value?.cancel()
                        hoverExitJob.value = null
                        currentItem.onHover.onHover(currentItem, true)
                    } else {
                        hoverExitJob.value = scope.launch {
                            delay(currentItem.hoverCancelDurationMillis)
                            if (!isHovering.value) {
                                currentItem.onHover.onHover(currentItem, false)
                                currentItem.parentItemDynamicSize.value = currentItem.size
                                selectedItemAfterHover?.first?.let {
                                    it.parentItemDynamicSize.value =
                                        it.size * it.onSelectItemSizeChangeFriction
                                }
                                state.updateExpandedItem(
                                    selectedItemAfterHover?.first,
                                    selectedItemAfterHover?.second,
                                )
                            }
                        }
                    }
                }
        } else {
            Modifier
        },
    ) {
        AnimatedVisibility(
            visible = currentItem != null && selectedParentAnchor != null,
            enter = getEnterTransition(animationType),
            exit = getExitTransition(animationType),
        ) {
            val childItem: @Composable (Int, TinyGlideItem) -> Unit = { childIndex, item ->
                var isHovered by remember(item) { mutableStateOf(false) }
                val animatedChildScale by animateFloatAsState(
                    targetValue = if (isHovered) {
                        item.onSelectItemSizeChangeFriction
                    } else {
                        1f
                    },
                    animationSpec = tween(item.onSelectItemSizeChangeDurationMillis),
                )
                val interactionSource = remember(item) { MutableInteractionSource() }
                val spacingModifier = when (orientation) {
                    TinyGlideOrientation.HORIZONTAL -> Modifier.width(item.itemSeparationSpace)
                    TinyGlideOrientation.VERTICAL -> Modifier.height(item.itemSeparationSpace)
                }
                val transformOrigin = when (orientation) {
                    TinyGlideOrientation.HORIZONTAL -> TransformOrigin(0.5f, 1f)
                    TinyGlideOrientation.VERTICAL -> TransformOrigin(1f, 0.5f)
                }
                Spacer(spacingModifier)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(item.size)
                        .graphicsLayer {
                            scaleX = animatedChildScale
                            scaleY = animatedChildScale
                            this.transformOrigin = transformOrigin
                        }
                        .background(item.backgroundColor, item.shape)
                        .hoverEffect { onHover ->
                            isHovered = onHover
                            item.onHover.onHover(item, onHover)
                            val position = TinyGlideItemPosition(
                                parentIndex = state.expandedIndex ?: 0,
                                childIndex = childIndex,
                            )
                            if (onHover) {
                                state.updateHoveredItem(item, position)
                            } else if (
                                state.hoveredItem === item && state.hoveredPosition == position
                            ) {
                                state.updateHoveredItem(null, null)
                            }
                        }
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            item.onClick.onClick(item, childIndex)
                            tinyGlideActionListener.onSubItemClickListener(
                                item,
                                Pair(state.expandedIndex ?: 0, childIndex),
                            )
                        },
                ) {
                    Icon(
                        painter = painterResource(item.icon.selectedResource),
                        contentDescription = item.icon.contentDescription,
                        tint = item.icon.selectedTint,
                        modifier = item.icon.modifier.then(
                            Modifier.size(item.size - item.icon.sizeReduction),
                        ),
                    )
                }
                Spacer(spacingModifier)
            }
            when (orientation) {
                TinyGlideOrientation.HORIZONTAL -> Row(verticalAlignment = Alignment.Bottom) {
                    subItems.forEachIndexed { childIndex, item ->
                        childItem(childIndex, item)
                    }
                }

                TinyGlideOrientation.VERTICAL -> Column(horizontalAlignment = Alignment.End) {
                    subItems.forEachIndexed { childIndex, item ->
                        childItem(childIndex, item)
                    }
                }
            }
        }
    }
}

private fun keepGroupInsideContainer(
    preferredOffset: Dp,
    groupSize: Dp,
    containerSize: Dp,
    edgePadding: Dp,
): Dp {
    val maximumOffset = containerSize - groupSize - edgePadding
    return if (maximumOffset >= edgePadding) {
        preferredOffset.coerceIn(edgePadding, maximumOffset)
    } else {
        ((containerSize - groupSize) / 2).coerceAtLeast(0.dp)
    }
}
