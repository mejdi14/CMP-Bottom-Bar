package org.mejdi14.tinyGlide.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

@OptIn(InternalResourceApi::class)
class TinyGlideStateTest {
    @Test
    fun selectionSurvivesReorderingByKey() {
        val first = item("first")
        val second = item("second")
        val state = TinyGlideState()

        state.attach(listOf(first, second)) {}
        state.select("second")
        state.attach(listOf(second.copy(), first.copy())) {}

        assertEquals("second", state.selectedKey)
        assertEquals(0, state.selectedIndex)
        assertEquals("second", state.expandedKey)
        assertEquals(0, state.expandedIndex)
    }

    @Test
    fun keyBasedInitialSelectionAndActionsResolveCurrentIndexes() {
        val state = TinyGlideState(initialSelectedKey = "third")
        val items = listOf(item("first"), item("second"), item("third"))

        state.attach(items) {}
        assertEquals(2, state.selectedIndex)

        state.expand("first")
        assertEquals("first", state.expandedKey)
        assertEquals(0, state.expandedIndex)

        state.deselect()
        assertNull(state.selectedKey)
        assertNull(state.expandedKey)
    }

    @Test
    fun duplicateSiblingKeysAreRejected() {
        val state = TinyGlideState()

        assertFailsWith<IllegalArgumentException> {
            state.attach(listOf(item("same"), item("same"))) {}
        }
    }

    @Test
    fun selectionAndDismissEventsHaveStableOrdering() {
        val events = mutableListOf<String>()
        val state = TinyGlideState()
        state.actionListener = listener(events)
        state.attach(listOf(item("first"), item("second"))) {}

        state.select("second")
        state.dismiss()

        assertEquals(
            listOf("select:1", "expand:1", "collapse:1", "deselect:1", "dismiss:1"),
            events,
        )
    }

    @Test
    fun hoverAndFocusStateExposeCurrentPositions() {
        val state = TinyGlideState()
        val parent = item("parent")
        val child = item("child")
        val parentPosition = TinyGlideItemPosition(parentIndex = 0)
        val childPosition = TinyGlideItemPosition(parentIndex = 0, childIndex = 0)
        state.attach(listOf(parent)) {}

        state.updateHoveredItem(parent, parentPosition)
        state.updateFocusedItem(child, childPosition)

        assertEquals("parent", state.hoveredItem?.key)
        assertEquals(parentPosition, state.hoveredPosition)
        assertEquals("child", state.focusedItem?.key)
        assertEquals(childPosition, state.focusedPosition)
    }

    private fun item(key: String): TinyGlideItem = TinyGlideItem(
        key = key,
        icon = BottomBarIcon(
            resource = DrawableResource("resource-$key", emptySet()),
            contentDescription = key,
        ),
    )

    private fun listener(events: MutableList<String>): TinyGlideActionListener =
        object : TinyGlideActionListener {
            override fun onClick(item: TinyGlideItem, index: Int?) = Unit

            override fun onSubItemClickListener(
                item: TinyGlideItem,
                index: Pair<Int, Int>,
            ) = Unit

            override fun onSelect(item: TinyGlideItem, index: Int) {
                events += "select:$index"
            }

            override fun onDeselect(item: TinyGlideItem, index: Int) {
                events += "deselect:$index"
            }

            override fun onExpand(item: TinyGlideItem, index: Int) {
                events += "expand:$index"
            }

            override fun onCollapse(item: TinyGlideItem, index: Int) {
                events += "collapse:$index"
            }

            override fun onDismiss(selectedItem: TinyGlideItem?, selectedIndex: Int?) {
                events += "dismiss:$selectedIndex"
            }
        }
}
