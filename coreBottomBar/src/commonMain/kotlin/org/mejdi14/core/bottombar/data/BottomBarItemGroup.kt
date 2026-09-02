package org.mejdi14.core.bottombar.data

data class BottomBarItemGroup<out T : BottomBarItem>(
    val items: List<T>,
    val key: String? = null,
) {
    init {
        require(items.isNotEmpty()) { "A bottom bar group must contain at least one item." }
    }
}
