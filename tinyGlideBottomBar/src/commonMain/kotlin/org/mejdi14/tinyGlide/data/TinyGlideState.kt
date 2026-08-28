package org.mejdi14.tinyGlide.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

@Stable
class TinyGlideState internal constructor() {
    internal val selectedIndexState = mutableStateOf<Int?>(null)
    internal val expandedItemState = mutableStateOf<TinyGlideItem?>(null)
    internal var actionListener: TinyGlideActionListener? = null

    private val selectedItemState = mutableStateOf<TinyGlideItem?>(null)
    private val expandedIndexState = mutableStateOf<Int?>(null)
    private val hoveredItemState = mutableStateOf<TinyGlideItem?>(null)
    private val hoveredPositionState = mutableStateOf<TinyGlideItemPosition?>(null)

    val selectedItem: TinyGlideItem?
        get() = selectedItemState.value

    val selectedIndex: Int?
        get() = selectedIndexState.value

    val expandedItem: TinyGlideItem?
        get() = expandedItemState.value

    val expandedIndex: Int?
        get() = expandedIndexState.value

    val hoveredItem: TinyGlideItem?
        get() = hoveredItemState.value

    val hoveredPosition: TinyGlideItemPosition?
        get() = hoveredPositionState.value

    val isExpanded: Boolean
        get() = expandedItemState.value != null

    internal fun updateSelection(item: TinyGlideItem?, index: Int?) {
        val previousItem = selectedItemState.value
        val previousIndex = selectedIndexState.value
        if (previousItem === item && previousIndex == index) return

        if (previousItem != null && previousIndex != null) {
            actionListener?.onDeselect(previousItem, previousIndex)
        }
        selectedItemState.value = item
        selectedIndexState.value = index
        if (item != null && index != null) {
            actionListener?.onSelect(item, index)
        }
    }

    internal fun updateExpandedItem(item: TinyGlideItem?, index: Int?) {
        val previousItem = expandedItemState.value
        val previousIndex = expandedIndexState.value
        if (previousItem === item && previousIndex == index) return

        if (previousItem != null && previousIndex != null) {
            actionListener?.onCollapse(previousItem, previousIndex)
        }
        expandedItemState.value = item
        expandedIndexState.value = index
        if (item != null && index != null) {
            actionListener?.onExpand(item, index)
        }
    }

    internal fun updateHoveredItem(item: TinyGlideItem?, position: TinyGlideItemPosition?) {
        val previousItem = hoveredItemState.value
        val previousPosition = hoveredPositionState.value
        if (previousItem === item && previousPosition == position) return

        if (previousItem != null && previousPosition != null) {
            actionListener?.onHoverExit(previousItem, previousPosition)
        }
        hoveredItemState.value = item
        hoveredPositionState.value = position
        if (item != null && position != null) {
            actionListener?.onHover(item, position)
        }
    }

    fun dismiss() {
        val dismissedItem = selectedItemState.value
        val dismissedIndex = selectedIndexState.value
        expandedItemState.value?.let { item ->
            item.parentItemDynamicSize.value = item.size
        }
        updateHoveredItem(null, null)
        updateExpandedItem(null, null)
        updateSelection(null, null)
        actionListener?.onDismiss(dismissedItem, dismissedIndex)
    }
}

@Composable
fun rememberTinyGlideState(): TinyGlideState = remember { TinyGlideState() }
