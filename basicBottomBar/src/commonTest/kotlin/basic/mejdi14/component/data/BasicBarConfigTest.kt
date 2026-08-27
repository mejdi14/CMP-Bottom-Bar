package basic.mejdi14.component.data

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BasicBarConfigTest {
    @Test
    fun defaultsDescribeABottomHorizontalBar() {
        val config = BasicBarConfig()

        assertEquals(BasicBarPosition.HorizontalBottom, config.position)
        assertTrue(config.position.isHorizontal)
        assertFalse(BasicBarPosition.VerticalLeft.isHorizontal)
    }

    @Test
    fun invalidDimensionsAreRejected() {
        assertFailsWith<IllegalArgumentException> { BasicBarConfig(itemSize = 0.dp) }
        assertFailsWith<IllegalArgumentException> { BasicBarConfig(itemSpacing = (-1).dp) }
    }

    @Test
    fun stateSupportsSelectionAndDeselection() {
        val state = BasicBarState(initialSelectedIndex = 1)

        assertEquals(1, state.selectedIndex)
        state.select(3)
        assertEquals(3, state.selectedIndex)
        state.select(null)
        assertNull(state.selectedIndex)
        assertFailsWith<IllegalArgumentException> { state.select(-1) }
    }
}
