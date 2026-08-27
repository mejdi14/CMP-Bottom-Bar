package org.mejdi14.core.bottombar.listener

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BottomBarListenerTest {
    @Test
    fun clickListenerReceivesItemAndOptionalIndex() {
        var receivedItem: String? = null
        var receivedIndex: Int? = null
        val listener = BottomBarClickListener<String> { item, index ->
            receivedItem = item
            receivedIndex = index
        }

        listener.onClick("home", 2)
        assertEquals("home", receivedItem)
        assertEquals(2, receivedIndex)

        listener.onClick("action", null)
        assertEquals("action", receivedItem)
        assertNull(receivedIndex)
    }

    @Test
    fun hoverListenerReceivesCurrentState() {
        var hovered = false
        val listener = BottomBarHoverListener<String> { item, isHovered ->
            assertEquals("home", item)
            hovered = isHovered
        }

        listener.onHover("home", true)

        assertTrue(hovered)
    }
}
