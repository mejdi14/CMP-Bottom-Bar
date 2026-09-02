package org.mejdi14.tinyGlide.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideItemPosition
import org.mejdi14.tinyGlide.data.TinyGlideItemVisualState
import org.mejdi14.tinyGlide.data.TinyGlideChildrenLayout
import org.mejdi14.tinyGlide.data.TinyGlideState
import org.mejdi14.tinyGlide.data.rememberTinyGlideState
import org.mejdi14.tinyGlide.enum.TinyGlideChildrenPlacement
import org.mejdi14.tinyGlide.enum.TinyGlideOrientation
import org.mejdi14.tinyGlide.enum.TinyGlideVerticalSide
import org.mejdi14.tinyGlide.helper.handleHoverAction
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

@Composable
fun TinyGlideBottomBar(
    bottomBarItems: List<TinyGlideItem>,
    parentModifier: Modifier,
    tinyGlideActionListener: TinyGlideActionListener,
    state: TinyGlideState = rememberTinyGlideState(),
    orientation: TinyGlideOrientation = TinyGlideOrientation.HORIZONTAL,
    verticalSide: TinyGlideVerticalSide = TinyGlideVerticalSide.END,
    childrenPlacement: TinyGlideChildrenPlacement = TinyGlideChildrenPlacement.AUTO,
    edgePadding: Dp = 5.dp,
    closeOnItemSelect: Boolean = true,
    childrenLayout: TinyGlideChildrenLayout = TinyGlideChildrenLayout(),
    customChildrenContent: TinyGlideCustomChildrenContent? = null,
    showCustomChildrenContent: (TinyGlideItem) -> Boolean = { true },
    parentContent: TinyGlideItemContent = { item, _, visualState ->
        TinyGlideDefaultParentContent(item, visualState)
    },
    childContent: TinyGlideItemContent = { item, _, visualState ->
        TinyGlideDefaultChildContent(item, visualState)
    },
) {
    val selectedIndex = state.selectedIndexState
    val lazyListState = rememberLazyListState()
    val itemBounds = remember(orientation) { mutableStateMapOf<String, Rect>() }
    val containerPosition = remember { mutableStateOf(Offset.Zero) }
    val containerSize = remember { mutableStateOf(IntSize.Zero) }
    val hoverExitJob = remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val isHovering = remember { mutableStateOf(false) }
    val focusRequesters = remember(bottomBarItems.map(TinyGlideItem::key)) {
        bottomBarItems.associate { it.key to FocusRequester() }
    }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val orientationModifier = Modifier.fillMaxSize()

    SideEffect {
        state.attach(bottomBarItems) { index ->
            lazyListState.animateScrollToItem(index)
        }
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
            .padding(edgePadding)
            .onGloballyPositioned { coordinates ->
                containerPosition.value = coordinates.positionInRoot()
                containerSize.value = coordinates.size
            },
    ) {
        val parentItem: @Composable (Int, TinyGlideItem) -> Unit = { index, item ->
            val position = TinyGlideItemPosition(parentIndex = index)
            val isSelected = state.selectedKey == item.key
            val isExpanded = state.expandedKey == item.key
            val isHovered = state.hoveredItem?.key == item.key &&
                state.hoveredPosition == position
            val isFocused = state.focusedItem?.key == item.key &&
                state.focusedPosition == position
            val usesHoverAnimation = isHovered || isFocused || (isExpanded && !isSelected)
            val targetScale = when {
                usesHoverAnimation -> item.animation.parentHoverScale
                isSelected -> item.animation.parentSelectedScale
                else -> 1f
            }
            val animatedParentSize by animateDpAsState(
                targetValue = item.size * targetScale,
                animationSpec = tween(
                    durationMillis = if (usesHoverAnimation) {
                        item.animation.parentHoverDurationMillis
                    } else {
                        item.animation.parentSelectionDurationMillis
                    },
                    easing = if (usesHoverAnimation) {
                        item.animation.parentHoverEasing
                    } else {
                        item.animation.parentSelectionEasing
                    },
                ),
            )
            val spacingModifier = when (orientation) {
                TinyGlideOrientation.HORIZONTAL -> Modifier.width(item.itemSeparationSpace)
                TinyGlideOrientation.VERTICAL -> Modifier.height(item.itemSeparationSpace)
            }
            val interactionSource = remember(item.key) { MutableInteractionSource() }
            val activate = {
                if (item.interaction.shouldDispatchClick(selectedIndex.value, index)) {
                    val nextIndex = item.interaction.nextSelectedIndex(selectedIndex.value, index)
                    val nextSelectedItem = nextIndex?.let(bottomBarItems::getOrNull)
                    item.onClick.onClick(item, index)
                    tinyGlideActionListener.onClick(item, index)
                    state.updateSelection(nextSelectedItem, nextIndex)
                    state.updateExpandedItem(nextSelectedItem, nextIndex)
                }
            }
            val moveFocus = { offset: Int ->
                val nextIndex = (index + offset).coerceIn(bottomBarItems.indices)
                if (nextIndex != index) {
                    scope.launch {
                        lazyListState.animateScrollToItem(nextIndex)
                        focusRequesters[bottomBarItems[nextIndex].key]?.requestFocus()
                    }
                    true
                } else {
                    false
                }
            }
            Spacer(spacingModifier)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(animatedParentSize)
                    .onGloballyPositioned { layoutCoordinates ->
                        val rootPosition = layoutCoordinates.positionInRoot()
                        val bounds = Rect(
                            left = rootPosition.x,
                            top = rootPosition.y,
                            right = rootPosition.x + layoutCoordinates.size.width,
                            bottom = rootPosition.y + layoutCoordinates.size.height,
                        )
                        if (itemBounds[item.key] != bounds) {
                            itemBounds[item.key] = bounds
                        }
                    }
                    .background(
                        color = if (isExpanded) {
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
                                state.selectedItem?.let { selected ->
                                    state.selectedIndex?.let { selected to it }
                                }
                            },
                        )
                    }
                    .focusRequester(focusRequesters.getValue(item.key))
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            hoverExitJob.value?.cancel()
                            state.updateFocusedItem(item, position)
                            state.updateExpandedItem(item, index)
                        } else if (
                            state.focusedItem?.key == item.key && state.focusedPosition == position
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

                                Key.DirectionLeft -> orientation == TinyGlideOrientation.HORIZONTAL &&
                                    moveFocus(if (isRtl) 1 else -1)

                                Key.DirectionRight -> orientation == TinyGlideOrientation.HORIZONTAL &&
                                    moveFocus(if (isRtl) -1 else 1)

                                Key.DirectionUp -> orientation == TinyGlideOrientation.VERTICAL &&
                                    moveFocus(-1)

                                Key.DirectionDown -> orientation == TinyGlideOrientation.VERTICAL &&
                                    moveFocus(1)

                                else -> false
                            }
                        }
                    }
                    .semantics(mergeDescendants = true) {
                        item.icon.contentDescription?.let { contentDescription = it }
                        role = Role.Button
                        selected = isSelected
                        stateDescription = when {
                            isSelected && isExpanded -> "Selected and expanded"

                            isSelected -> "Selected"
                            isExpanded -> "Expanded"
                            else -> "Collapsed"
                        }
                    }
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        role = Role.Button,
                        onClick = activate,
                    )
                    .focusable(interactionSource = interactionSource),
            ) {
                parentContent(
                    item,
                    position,
                    TinyGlideItemVisualState(
                        isSelected = isSelected,
                        isExpanded = isExpanded,
                        isHovered = isHovered,
                        isFocused = isFocused,
                        displaySize = animatedParentSize,
                    ),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                userScrollEnabled = true,
            ) {
                itemsIndexed(bottomBarItems, key = { _, item -> item.key }) { index, item ->
                    parentItem(index, item)
                }
            }

            TinyGlideOrientation.VERTICAL -> LazyColumn(
                state = lazyListState,
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = when (verticalSide) {
                    TinyGlideVerticalSide.START -> Alignment.Start
                    TinyGlideVerticalSide.END -> Alignment.End
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .align(
                        when (verticalSide) {
                            TinyGlideVerticalSide.START -> Alignment.CenterStart
                            TinyGlideVerticalSide.END -> Alignment.CenterEnd
                        },
                    ),
                userScrollEnabled = true,
            ) {
                itemsIndexed(bottomBarItems, key = { _, item -> item.key }) { index, item ->
                    parentItem(index, item)
                }
            }
        }

        val selectedParentBounds = state.expandedKey
            ?.let(itemBounds::get)
            ?.let { rootBounds ->
                Rect(
                    left = rootBounds.left - containerPosition.value.x,
                    top = rootBounds.top - containerPosition.value.y,
                    right = rootBounds.right - containerPosition.value.x,
                    bottom = rootBounds.bottom - containerPosition.value.y,
                )
            }
        SubItemsComposable(
            state = state,
            selectedParentBounds = selectedParentBounds,
            hoverExitJob = hoverExitJob,
            isHovering = isHovering,
            scope = scope,
            tinyGlideActionListener = tinyGlideActionListener,
            orientation = orientation,
            verticalSide = verticalSide,
            childrenPlacement = childrenPlacement,
            edgePadding = edgePadding,
            containerSize = containerSize.value,
            closeOnItemSelect = closeOnItemSelect,
            childrenLayout = childrenLayout,
            customChildrenContent = customChildrenContent,
            showCustomChildrenContent = showCustomChildrenContent,
            childContent = childContent,
        )
    }
}
