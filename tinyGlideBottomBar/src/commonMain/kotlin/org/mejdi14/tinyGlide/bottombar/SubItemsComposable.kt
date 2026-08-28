package org.mejdi14.tinyGlide.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mejdi14.tinyGlide.animation.getEnterTransition
import org.mejdi14.tinyGlide.animation.getExitTransition
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.enum.AnimationType
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SubItemsComposable(
    selectedItem: MutableState<TinyGlideItem?>,
    selectedIndex: MutableState<Int?>,
    selectedParentAnchor: Offset?,
    selectedItemAfterHover: TinyGlideItem?,
    hoverExitJob: MutableState<Job?>,
    isHovering: MutableState<Boolean>,
    scope: CoroutineScope,
    tinyGlideActionListener: TinyGlideActionListener,
    animationType: AnimationType = AnimationType.SCALE,
) {
    val currentItem = selectedItem.value
    val subItems = currentItem?.subTinyGlideItems.orEmpty()
    val density = LocalDensity.current
    val groupWidth = subItems.fold(0.dp) { width, item ->
        width + item.size + (item.itemSeparationSpace * 2)
    }
    val groupHeight = subItems.maxOfOrNull { it.size } ?: 0.dp
    val parentCenterX = with(density) { (selectedParentAnchor?.x ?: 0f).toDp() }
    val parentTop = with(density) { (selectedParentAnchor?.y ?: 0f).toDp() }

    Column(
        modifier = if (currentItem != null && selectedParentAnchor != null) {
            Modifier
                .offset(
                    x = parentCenterX - (groupWidth / 2),
                    y = parentTop - currentItem.parentAndSubVerticalSeparationSpace - groupHeight,
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
                                selectedItemAfterHover?.let {
                                    it.parentItemDynamicSize.value =
                                        it.size * it.onSelectItemSizeChangeFriction
                                }
                                selectedItem.value = selectedItemAfterHover
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
            Row(verticalAlignment = Alignment.Bottom) {
                subItems.forEachIndexed { childIndex, item ->
                    var isHovered by remember(item) { mutableStateOf(false) }
                    val animatedChildSize by animateDpAsState(
                        targetValue = if (isHovered) {
                            item.size * item.onSelectItemSizeChangeFriction
                        } else {
                            item.size
                        },
                        animationSpec = tween(item.onSelectItemSizeChangeDurationMillis),
                    )
                    val interactionSource = remember(item) { MutableInteractionSource() }
                    Spacer(Modifier.width(item.itemSeparationSpace))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(animatedChildSize)
                            .background(item.backgroundColor, item.shape)
                            .hoverEffect { isHovered = it }
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                            ) {
                            item.onClick.onClick(item, childIndex)
                            tinyGlideActionListener.onSubItemClickListener(
                                item,
                                Pair(selectedIndex.value ?: 0, childIndex),
                            )
                        },
                    ) {
                        Icon(
                            painter = painterResource(item.icon.selectedResource),
                            contentDescription = item.icon.contentDescription,
                            tint = item.icon.selectedTint,
                            modifier = item.icon.modifier.then(
                                Modifier.size(animatedChildSize - item.icon.sizeReduction),
                            ),
                        )
                    }
                    Spacer(Modifier.width(item.itemSeparationSpace))
                }
            }
        }
    }
}
