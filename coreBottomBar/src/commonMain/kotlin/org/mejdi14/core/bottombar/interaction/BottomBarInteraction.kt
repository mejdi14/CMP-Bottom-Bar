package org.mejdi14.core.bottombar.interaction

/** Defines how clicking an item changes the selected index. */
enum class BottomBarSelectionMode {
    SELECT,
    TOGGLE,
    NONE,
}

/**
 * Describes item interaction without storing mutable UI state in the item model.
 */
data class BottomBarInteraction(
    val enabled: Boolean = true,
    val selectionMode: BottomBarSelectionMode = BottomBarSelectionMode.SELECT,
    val dispatchClickWhenSelected: Boolean = false,
) {
    fun shouldDispatchClick(currentIndex: Int?, clickedIndex: Int): Boolean =
        enabled && (dispatchClickWhenSelected || currentIndex != clickedIndex)

    fun nextSelectedIndex(currentIndex: Int?, clickedIndex: Int): Int? {
        if (!enabled) return currentIndex

        return when (selectionMode) {
            BottomBarSelectionMode.SELECT -> clickedIndex
            BottomBarSelectionMode.TOGGLE -> if (currentIndex == clickedIndex) null else clickedIndex
            BottomBarSelectionMode.NONE -> currentIndex
        }
    }
}
