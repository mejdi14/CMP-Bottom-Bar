package org.mejdi14.tinyGlide.helper

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideItemPosition
import org.mejdi14.tinyGlide.data.TinyGlideState

internal fun handleHoverAction(
    isHovering: MutableState<Boolean>,
    onHover: Boolean,
    item: TinyGlideItem,
    itemIndex: Int,
    state: TinyGlideState,
    hoverExitJob: MutableState<Job?>,
    scope: CoroutineScope,
    selectedItemAfterHover: () -> Pair<TinyGlideItem, Int>?,
) {
    isHovering.value = onHover
    item.onHover.onHover(item, onHover)
    if (onHover) {
        hoverExitJob.value?.cancel()
        hoverExitJob.value = null
        if (state.expandedItem !== item) {
            state.expandedItem?.parentItemDynamicSize?.value = state.expandedItem?.size ?: item.size
        }
        state.updateHoveredItem(item, TinyGlideItemPosition(parentIndex = itemIndex))
        state.updateExpandedItem(item, itemIndex)
        item.parentItemDynamicSize.value = item.size * item.onSelectItemSizeChangeFriction
    } else {
        if (state.hoveredItem === item) {
            state.updateHoveredItem(null, null)
        }
        hoverExitJob.value = scope.launch {
            delay(item.hoverCancelDurationMillis)
            if (!isHovering.value) {
                item.parentItemDynamicSize.value = item.size
                val fallback = selectedItemAfterHover()
                fallback?.first?.let { selected ->
                    selected.parentItemDynamicSize.value =
                        selected.size * selected.onSelectItemSizeChangeFriction
                }
                state.updateExpandedItem(fallback?.first, fallback?.second)
            }
        }
    }
}
