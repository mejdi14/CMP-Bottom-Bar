package org.mejdi14.tinyGlide.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

@Stable
class TinyGlideState(
    initialSelectedIndex: Int? = null,
    initialExpandedIndex: Int? = initialSelectedIndex,
    initialSelectedKey: String? = null,
    initialExpandedKey: String? = initialSelectedKey,
) {
    internal val selectedIndexState = mutableStateOf<Int?>(null)
    internal val expandedItemState = mutableStateOf<TinyGlideItem?>(null)
    internal var actionListener: TinyGlideActionListener? = null

    private val selectedItemState = mutableStateOf<TinyGlideItem?>(null)
    private val selectedKeyState = mutableStateOf<String?>(null)
    private val expandedIndexState = mutableStateOf<Int?>(null)
    private val expandedKeyState = mutableStateOf<String?>(null)
    private val hoveredItemState = mutableStateOf<TinyGlideItem?>(null)
    private val hoveredPositionState = mutableStateOf<TinyGlideItemPosition?>(null)
    private val focusedItemState = mutableStateOf<TinyGlideItem?>(null)
    private val focusedPositionState = mutableStateOf<TinyGlideItemPosition?>(null)
    private var pendingSelectedIndex = initialSelectedIndex
    private var pendingExpandedIndex = initialExpandedIndex
    private var pendingSelectedKey = initialSelectedKey
    private var pendingExpandedKey = initialExpandedKey
    private var items: List<TinyGlideItem> = emptyList()
    private var scrollHandler: suspend (Int) -> Unit = {}

    val selectedItem: TinyGlideItem?
        get() = selectedItemState.value

    val selectedIndex: Int?
        get() = selectedIndexState.value

    val selectedKey: String?
        get() = selectedKeyState.value

    val expandedItem: TinyGlideItem?
        get() = expandedItemState.value

    val expandedIndex: Int?
        get() = expandedIndexState.value

    val expandedKey: String?
        get() = expandedKeyState.value

    val hoveredItem: TinyGlideItem?
        get() = hoveredItemState.value

    val hoveredPosition: TinyGlideItemPosition?
        get() = hoveredPositionState.value

    val focusedItem: TinyGlideItem?
        get() = focusedItemState.value

    val focusedPosition: TinyGlideItemPosition?
        get() = focusedPositionState.value

    val isExpanded: Boolean
        get() = expandedItemState.value != null

    internal fun attach(
        items: List<TinyGlideItem>,
        scrollHandler: suspend (Int) -> Unit,
    ) {
        validateKeys(items)
        this.items = items
        this.scrollHandler = scrollHandler

        val selectedIndex = resolveIndex(
            key = pendingSelectedKey ?: selectedKeyState.value,
            index = pendingSelectedIndex,
        )
        val expandedIndex = resolveIndex(
            key = pendingExpandedKey ?: expandedKeyState.value,
            index = pendingExpandedIndex,
        )
        selectedIndexState.value = selectedIndex
        selectedItemState.value = selectedIndex?.let(items::getOrNull)
        selectedKeyState.value = selectedItemState.value?.key
        expandedIndexState.value = expandedIndex
        expandedItemState.value = expandedIndex?.let(items::getOrNull)
        expandedKeyState.value = expandedItemState.value?.key
        pendingSelectedIndex = null
        pendingExpandedIndex = null
        pendingSelectedKey = null
        pendingExpandedKey = null
    }

    fun select(index: Int) {
        val item = items.getOrNull(index) ?: return
        updateSelection(item, index)
        updateExpandedItem(item, index)
    }

    fun select(key: String) {
        findIndex(key)?.let(::select)
    }

    fun deselect() {
        updateSelection(null, null)
        updateExpandedItem(null, null)
    }

    fun expand(index: Int) {
        val item = items.getOrNull(index) ?: return
        updateExpandedItem(item, index)
    }

    fun expand(key: String) {
        findIndex(key)?.let(::expand)
    }

    fun collapse() {
        updateExpandedItem(null, null)
    }

    suspend fun scrollTo(index: Int) {
        if (index in items.indices) {
            scrollHandler(index)
        }
    }

    suspend fun scrollTo(key: String) {
        findIndex(key)?.let { scrollHandler(it) }
    }

    internal fun updateSelection(item: TinyGlideItem?, index: Int?) {
        val previousItem = selectedItemState.value
        val previousIndex = selectedIndexState.value
        if (selectedKeyState.value == item?.key && previousIndex == index) return

        if (previousItem != null && previousIndex != null) {
            actionListener?.onDeselect(previousItem, previousIndex)
        }
        selectedItemState.value = item
        selectedIndexState.value = index
        selectedKeyState.value = item?.key
        if (item != null && index != null) {
            actionListener?.onSelect(item, index)
        }
    }

    internal fun updateExpandedItem(item: TinyGlideItem?, index: Int?) {
        val previousItem = expandedItemState.value
        val previousIndex = expandedIndexState.value
        if (expandedKeyState.value == item?.key && previousIndex == index) return

        if (previousItem != null && previousIndex != null) {
            actionListener?.onCollapse(previousItem, previousIndex)
        }
        expandedItemState.value = item
        expandedIndexState.value = index
        expandedKeyState.value = item?.key
        if (item != null && index != null) {
            actionListener?.onExpand(item, index)
        }
    }

    internal fun updateHoveredItem(item: TinyGlideItem?, position: TinyGlideItemPosition?) {
        val previousItem = hoveredItemState.value
        val previousPosition = hoveredPositionState.value
        if (previousItem?.key == item?.key && previousPosition == position) return

        if (previousItem != null && previousPosition != null) {
            actionListener?.onHoverExit(previousItem, previousPosition)
        }
        hoveredItemState.value = item
        hoveredPositionState.value = position
        if (item != null && position != null) {
            actionListener?.onHover(item, position)
        }
    }

    internal fun updateFocusedItem(item: TinyGlideItem?, position: TinyGlideItemPosition?) {
        val previousItem = focusedItemState.value
        val previousPosition = focusedPositionState.value
        if (previousItem?.key == item?.key && previousPosition == position) return

        if (previousItem != null && previousPosition != null) {
            actionListener?.onFocusExit(previousItem, previousPosition)
        }
        focusedItemState.value = item
        focusedPositionState.value = position
        if (item != null && position != null) {
            actionListener?.onFocus(item, position)
        }
    }

    fun dismiss() {
        val dismissedItem = selectedItemState.value
        val dismissedIndex = selectedIndexState.value
        updateFocusedItem(null, null)
        updateHoveredItem(null, null)
        updateExpandedItem(null, null)
        updateSelection(null, null)
        actionListener?.onDismiss(dismissedItem, dismissedIndex)
    }

    private fun findIndex(key: String): Int? = items
        .indexOfFirst { it.key == key }
        .takeIf { it >= 0 }

    private fun resolveIndex(key: String?, index: Int?): Int? {
        return key?.let(::findIndex) ?: index?.takeIf(items.indices::contains)
    }

    private fun validateKeys(items: List<TinyGlideItem>) {
        require(items.all { it.key.isNotBlank() }) { "Tiny Glide item keys cannot be blank" }
        require(items.map(TinyGlideItem::key).distinct().size == items.size) {
            "Tiny Glide item keys must be unique among siblings"
        }
        items.forEach { validateKeys(it.subTinyGlideItems) }
    }

    companion object {
        internal val Saver = listSaver<TinyGlideState, String>(
            save = {
                listOf(
                    it.selectedKey.orEmpty(),
                    it.expandedKey.orEmpty(),
                )
            },
            restore = {
                TinyGlideState(
                    initialSelectedKey = it[0].ifEmpty { null },
                    initialExpandedKey = it[1].ifEmpty { null },
                )
            },
        )
    }
}

@Composable
fun rememberTinyGlideState(
    initialSelectedIndex: Int? = null,
    initialSelectedKey: String? = null,
): TinyGlideState = rememberSaveable(
    initialSelectedIndex,
    initialSelectedKey,
    saver = TinyGlideState.Saver,
) {
    TinyGlideState(
        initialSelectedIndex = initialSelectedIndex,
        initialExpandedIndex = initialSelectedIndex,
        initialSelectedKey = initialSelectedKey,
        initialExpandedKey = initialSelectedKey,
    )
}
