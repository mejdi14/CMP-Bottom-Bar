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
        state.updateHoveredItem(item, TinyGlideItemPosition(parentIndex = itemIndex))
        state.updateExpandedItem(item, itemIndex)
    } else {
        if (state.hoveredItem?.key == item.key) {
            state.updateHoveredItem(null, null)
        }
        hoverExitJob.value = scope.launch {
            delay(item.hoverCancelDurationMillis)
            if (!isHovering.value && state.focusedItem == null) {
                val fallback = selectedItemAfterHover()
                state.updateExpandedItem(fallback?.first, fallback?.second)
            }
        }
    }
}
