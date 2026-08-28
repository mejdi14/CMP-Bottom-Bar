package org.mejdi14.tinyGlide.helper

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.isSelectedItem

internal fun handleHoverAction(
    isHovering: MutableState<Boolean>,
    onHover: Boolean,
    item: TinyGlideItem,
    selectedItem: MutableState<TinyGlideItem?>,
    hoverExitJob: MutableState<Job?>,
    scope: CoroutineScope,
    selectedItemAfterHover: () -> TinyGlideItem?,
) {
    isHovering.value = onHover
    item.onHover.onHover(item, onHover)
    if (onHover) {
        if (selectedItem.value !== item) {
            selectedItem.value?.let { previousItem ->
                previousItem.parentItemDynamicSize.value = previousItem.size
            }
        }
        if (item.isSelectedItem(selectedItem.value)) {
            hoverExitJob.value?.cancel()
            hoverExitJob.value = null
        }
        selectedItem.value = item
        item.parentItemDynamicSize.value =
            if (!item.isSelectedItem(selectedItem.value)) item.size else
                item.size * item.onSelectItemSizeChangeFriction
    } else {
        hoverExitJob.value = scope.launch {
            delay(item.hoverCancelDurationMillis)
            if (!isHovering.value) {
                item.parentItemDynamicSize.value = item.size
                val fallbackItem = selectedItemAfterHover()
                fallbackItem?.let {
                    it.parentItemDynamicSize.value = it.size * it.onSelectItemSizeChangeFriction
                }
                selectedItem.value = fallbackItem
            }
        }
    }
}
