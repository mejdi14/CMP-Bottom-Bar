package org.mejdi14.tinyGlide.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mejdi14.tinyGlide.animation.getEnterTransition
import org.mejdi14.tinyGlide.animation.getExitTransition
import org.mejdi14.tinyGlide.data.TinyGlideAnimationConfig
import org.mejdi14.tinyGlide.data.TinyGlideChildrenLayout
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideItemPosition
import org.mejdi14.tinyGlide.data.TinyGlideItemVisualState
import org.mejdi14.tinyGlide.data.TinyGlideState
import org.mejdi14.tinyGlide.enum.TinyGlideChildrenPlacement
import org.mejdi14.tinyGlide.enum.TinyGlideOrientation
import org.mejdi14.tinyGlide.enum.TinyGlideVerticalSide
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

@Composable
internal fun SubItemsComposable(
    state: TinyGlideState,
    selectedParentBounds: Rect?,
    hoverExitJob: MutableState<Job?>,
    isHovering: MutableState<Boolean>,
    scope: CoroutineScope,
    tinyGlideActionListener: TinyGlideActionListener,
    orientation: TinyGlideOrientation,
    verticalSide: TinyGlideVerticalSide,
    childrenPlacement: TinyGlideChildrenPlacement,
    edgePadding: Dp,
    containerSize: IntSize,
    childrenLayout: TinyGlideChildrenLayout,
    customChildrenContent: TinyGlideCustomChildrenContent?,
    showCustomChildrenContent: (TinyGlideItem) -> Boolean,
    childContent: TinyGlideItemContent,
) {
    val currentItem = state.expandedItem
    val currentParentIndex = state.expandedIndex
    var retainedItem by remember { mutableStateOf<TinyGlideItem?>(null) }
    var retainedParentBounds by remember { mutableStateOf<Rect?>(null) }
    var retainedParentIndex by remember { mutableStateOf(0) }
    SideEffect {
        if (currentItem != null) {
            retainedItem = currentItem
        }
        if (currentParentIndex != null) {
            retainedParentIndex = currentParentIndex
        }
        if (selectedParentBounds != null) {
            retainedParentBounds = selectedParentBounds
        }
    }
    val displayedItem = currentItem ?: retainedItem
    val displayedParentBounds = selectedParentBounds ?: retainedParentBounds
    val displayedParentIndex = currentParentIndex ?: retainedParentIndex
    val subItems = displayedItem?.subTinyGlideItems.orEmpty()
    val layoutDirection = LocalLayoutDirection.current
    val resolvedPlacement = resolveTinyGlideChildrenPlacement(
        placement = childrenPlacement,
        orientation = orientation,
        verticalSide = verticalSide,
    )
    val childrenAreHorizontal = resolvedPlacement == TinyGlideChildrenPlacement.ABOVE ||
        resolvedPlacement == TinyGlideChildrenPlacement.BELOW
    val childrenAreOnLeft = when (resolvedPlacement) {
        TinyGlideChildrenPlacement.START -> layoutDirection == LayoutDirection.Ltr
        TinyGlideChildrenPlacement.END -> layoutDirection == LayoutDirection.Rtl
        else -> false
    }
    val density = LocalDensity.current
    val gridSize = tinyGlideGridSize(
        items = subItems,
        itemsPerLine = childrenLayout.itemsPerLine,
        childrenAreHorizontal = childrenAreHorizontal,
        lineSpacing = childrenLayout.lineSpacing,
    )
    val hasGrid = subItems.isNotEmpty()
    val hasCustomContent = displayedItem != null &&
        customChildrenContent != null &&
        showCustomChildrenContent(displayedItem)
    val contentSpacing = if (hasGrid && hasCustomContent) {
        childrenLayout.customContentSpacing
    } else {
        0.dp
    }
    val groupWidth = if (childrenAreHorizontal) {
        if (hasCustomContent) {
            maxOf(gridSize.width, childrenLayout.customContentSize.width)
        } else {
            gridSize.width
        }
    } else {
        gridSize.width +
            (if (hasCustomContent) childrenLayout.customContentSize.width else 0.dp) +
            contentSpacing
    }
    val groupHeight = if (childrenAreHorizontal) {
        gridSize.height +
            (if (hasCustomContent) childrenLayout.customContentSize.height else 0.dp) +
            contentSpacing
    } else {
        if (hasCustomContent) {
            maxOf(gridSize.height, childrenLayout.customContentSize.height)
        } else {
            gridSize.height
        }
    }
    val parentLeft = with(density) { (displayedParentBounds?.left ?: 0f).toDp() }
    val parentTop = with(density) { (displayedParentBounds?.top ?: 0f).toDp() }
    val parentRight = with(density) { (displayedParentBounds?.right ?: 0f).toDp() }
    val parentBottom = with(density) { (displayedParentBounds?.bottom ?: 0f).toDp() }
    val parentCenterX = with(density) { (displayedParentBounds?.center?.x ?: 0f).toDp() }
    val parentCenterY = with(density) { (displayedParentBounds?.center?.y ?: 0f).toDp() }
    val containerWidth = with(density) { containerSize.width.toDp() }
    val containerHeight = with(density) { containerSize.height.toDp() }
    val parentGap = displayedItem?.parentAndSubVerticalSeparationSpace ?: 0.dp
    val groupOffset = tinyGlideGroupOffset(
        placement = resolvedPlacement,
        childrenAreOnLeft = childrenAreOnLeft,
        parentLeft = parentLeft,
        parentTop = parentTop,
        parentRight = parentRight,
        parentBottom = parentBottom,
        parentCenterX = parentCenterX,
        parentCenterY = parentCenterY,
        parentGap = parentGap,
        groupWidth = groupWidth,
        groupHeight = groupHeight,
        containerWidth = containerWidth,
        containerHeight = containerHeight,
        edgePadding = edgePadding,
    )
    val childFocusRequesters = remember(displayedItem?.key, subItems.map(TinyGlideItem::key)) {
        subItems.associate { it.key to FocusRequester() }
    }
    val positionedModifier = if (displayedItem != null && displayedParentBounds != null) {
        Modifier.offset(x = groupOffset.x, y = groupOffset.y)
    } else {
        Modifier
    }

    Box(
        modifier = if (currentItem != null) {
            positionedModifier.hoverEffect { onHover ->
                    isHovering.value = onHover
                    if (onHover) {
                        hoverExitJob.value?.cancel()
                        hoverExitJob.value = null
                        currentItem.onHover.onHover(currentItem, true)
                    } else {
                        hoverExitJob.value = scope.launch {
                            delay(currentItem.hoverCancelDurationMillis)
                            if (!isHovering.value && state.focusedItem == null) {
                                currentItem.onHover.onHover(currentItem, false)
                                state.updateExpandedItem(
                                    state.selectedItem,
                                    state.selectedIndex,
                                )
                            }
                        }
                    }
                }
        } else {
            positionedModifier
        },
    ) {
        AnimatedVisibility(
            visible = currentItem != null && selectedParentBounds != null,
            enter = getEnterTransition(displayedItem?.animation ?: TinyGlideAnimationConfig()),
            exit = getExitTransition(displayedItem?.animation ?: TinyGlideAnimationConfig()),
        ) {
            val childItem: @Composable (Int, TinyGlideItem) -> Unit = { childIndex, item ->
                val position = TinyGlideItemPosition(
                    parentIndex = displayedParentIndex,
                    childIndex = childIndex,
                )
                var isHovered by remember(item.key) { mutableStateOf(false) }
                var isFocused by remember(item.key) { mutableStateOf(false) }
                val animatedChildScale by animateFloatAsState(
                    targetValue = if (isHovered || isFocused) {
                        item.animation.childHoverScale
                    } else {
                        1f
                    },
                    animationSpec = tween(
                        durationMillis = item.animation.childHoverDurationMillis,
                        easing = item.animation.childHoverEasing,
                    ),
                )
                val interactionSource = remember(item.key) { MutableInteractionSource() }
                val spacingModifier = if (childrenAreHorizontal) {
                    Modifier.width(item.itemSeparationSpace)
                } else {
                    Modifier.height(item.itemSeparationSpace)
                }
                val transformOrigin = when (resolvedPlacement) {
                    TinyGlideChildrenPlacement.ABOVE -> TransformOrigin(0.5f, 1f)
                    TinyGlideChildrenPlacement.BELOW -> TransformOrigin(0.5f, 0f)
                    TinyGlideChildrenPlacement.START,
                    TinyGlideChildrenPlacement.END,
                    -> if (childrenAreOnLeft) {
                        TransformOrigin(1f, 0.5f)
                    } else {
                        TransformOrigin(0f, 0.5f)
                    }

                    TinyGlideChildrenPlacement.AUTO -> TransformOrigin.Center
                }
                val activate = {
                    item.onClick.onClick(item, childIndex)
                    tinyGlideActionListener.onSubItemClickListener(
                        item,
                        Pair(displayedParentIndex, childIndex),
                    )
                }
                val moveFocus = { nextIndex: Int? ->
                    if (nextIndex != null && nextIndex in subItems.indices) {
                        childFocusRequesters[subItems[nextIndex].key]?.requestFocus()
                        true
                    } else {
                        false
                    }
                }
                val itemsPerLine = childrenLayout.itemsPerLine.coerceAtMost(subItems.size)
                val positionInLine = childIndex % itemsPerLine
                val previousInLine = if (positionInLine > 0) childIndex - 1 else null
                val nextInLine = if (
                    positionInLine < itemsPerLine - 1 && childIndex < subItems.lastIndex
                ) {
                    childIndex + 1
                } else {
                    null
                }
                val previousLine = (childIndex - itemsPerLine).takeIf { it >= 0 }
                val nextLine = (childIndex + itemsPerLine).takeIf { it <= subItems.lastIndex }
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
                            if (onHover) {
                                state.updateHoveredItem(item, position)
                            } else if (
                                state.hoveredItem?.key == item.key &&
                                state.hoveredPosition == position
                            ) {
                                state.updateHoveredItem(null, null)
                            }
                        }
                        .focusRequester(childFocusRequesters.getValue(item.key))
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            if (focusState.isFocused) {
                                hoverExitJob.value?.cancel()
                                state.updateFocusedItem(item, position)
                            } else if (
                                state.focusedItem?.key == item.key &&
                                state.focusedPosition == position
                            ) {
                                state.updateFocusedItem(null, null)
                                hoverExitJob.value = scope.launch {
                                    delay(item.hoverCancelDurationMillis)
                                    if (state.focusedItem == null && state.hoveredItem == null) {
                                        state.updateExpandedItem(
                                            state.selectedItem,
                                            state.selectedIndex,
                                        )
                                    }
                                }
                            }
                        }
                        .onPreviewKeyEvent { event ->
                            if (event.type != KeyEventType.KeyDown) {
                                false
                            } else {
                                when (event.key) {
                                    Key.Enter, Key.NumPadEnter, Key.Spacebar -> {
                                        activate()
                                        true
                                    }

                                    Key.Escape -> {
                                        state.dismiss()
                                        true
                                    }

                                    Key.DirectionLeft -> {
                                        val movesForward = layoutDirection == LayoutDirection.Rtl
                                        moveFocus(
                                            if (childrenAreHorizontal) {
                                                if (movesForward) nextInLine else previousInLine
                                            } else {
                                                if (movesForward) nextLine else previousLine
                                            },
                                        )
                                    }

                                    Key.DirectionRight -> {
                                        val movesForward = layoutDirection == LayoutDirection.Ltr
                                        moveFocus(
                                            if (childrenAreHorizontal) {
                                                if (movesForward) nextInLine else previousInLine
                                            } else {
                                                if (movesForward) nextLine else previousLine
                                            },
                                        )
                                    }

                                    Key.DirectionUp -> moveFocus(
                                        if (childrenAreHorizontal) previousLine else previousInLine,
                                    )

                                    Key.DirectionDown -> moveFocus(
                                        if (childrenAreHorizontal) nextLine else nextInLine,
                                    )
                                    else -> false
                                }
                            }
                        }
                        .semantics(mergeDescendants = true) {
                            item.icon.contentDescription?.let { contentDescription = it }
                            role = Role.Button
                        }
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            role = Role.Button,
                            onClick = activate,
                        )
                        .focusable(interactionSource = interactionSource),
                ) {
                    childContent(
                        item,
                        position,
                        TinyGlideItemVisualState(
                            isSelected = false,
                            isExpanded = false,
                            isHovered = isHovered,
                            isFocused = isFocused,
                            displaySize = item.size,
                        ),
                    )
                }
                Spacer(spacingModifier)
            }
            val indexedLines = subItems.withIndex().toList().chunked(
                childrenLayout.itemsPerLine.coerceAtLeast(1),
            )
            val grid: @Composable () -> Unit = {
                if (childrenAreHorizontal) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(childrenLayout.lineSpacing),
                    ) {
                        indexedLines.forEach { line ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                line.forEach { indexedItem ->
                                    childItem(indexedItem.index, indexedItem.value)
                                }
                            }
                        }
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(childrenLayout.lineSpacing),
                    ) {
                        indexedLines.forEach { line ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                line.forEach { indexedItem ->
                                    childItem(indexedItem.index, indexedItem.value)
                                }
                            }
                        }
                    }
                }
            }
            val customContent: @Composable () -> Unit = {
                if (displayedItem != null && customChildrenContent != null && hasCustomContent) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(childrenLayout.customContentSize),
                    ) {
                        customChildrenContent(
                            displayedItem,
                            TinyGlideItemPosition(parentIndex = displayedParentIndex),
                        )
                    }
                }
            }
            if (childrenAreHorizontal) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (resolvedPlacement == TinyGlideChildrenPlacement.ABOVE) {
                        customContent()
                        if (hasGrid && hasCustomContent) {
                            Spacer(Modifier.height(childrenLayout.customContentSpacing))
                        }
                        grid()
                    } else {
                        grid()
                        if (hasGrid && hasCustomContent) {
                            Spacer(Modifier.height(childrenLayout.customContentSpacing))
                        }
                        customContent()
                    }
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (childrenAreOnLeft) {
                        customContent()
                        if (hasGrid && hasCustomContent) {
                            Spacer(Modifier.width(childrenLayout.customContentSpacing))
                        }
                        grid()
                    } else {
                        grid()
                        if (hasGrid && hasCustomContent) {
                            Spacer(Modifier.width(childrenLayout.customContentSpacing))
                        }
                        customContent()
                    }
                }
            }
        }
    }
}
