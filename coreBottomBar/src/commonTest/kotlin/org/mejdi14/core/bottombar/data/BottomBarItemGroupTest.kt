package org.mejdi14.core.bottombar.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BottomBarItemGroupTest {
    @Test
    fun groupKeepsItemsAndKey() {
        val items = listOf(TestItem, TestItem)
        val group = BottomBarItemGroup(items = items, key = "tools")

        assertEquals(items, group.items)
        assertEquals("tools", group.key)
    }

    @Test
    fun groupRejectsEmptyItems() {
        assertFailsWith<IllegalArgumentException> {
            BottomBarItemGroup<TestItem>(emptyList())
        }
    }

    private data object TestItem : BottomBarItem {
        override val icon: BottomBarIcon
            get() = error("Not needed by this test")
    }
}
