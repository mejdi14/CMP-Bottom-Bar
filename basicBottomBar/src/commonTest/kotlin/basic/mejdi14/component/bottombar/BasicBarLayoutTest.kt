package basic.mejdi14.component.bottombar

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BasicBarLayoutTest {
    @Test
    fun stripSizeHandlesEmptyAndSpacedItems() {
        assertEquals(0.dp, basicBarStripSize(0, 50.dp, 10.dp))
        assertEquals(170.dp, basicBarStripSize(3, 50.dp, 10.dp))
    }

    @Test
    fun itemOffsetUsesAStableItemStride() {
        assertEquals(0.dp, basicBarItemOffset(0, 50.dp, 10.dp))
        assertEquals(120.dp, basicBarItemOffset(2, 50.dp, 10.dp))
    }

    @Test
    fun selectedIndexIsNormalizedAgainstCurrentItems() {
        assertEquals(2, normalizedBasicBarIndex(2, 3))
        assertNull(normalizedBasicBarIndex(3, 3))
        assertNull(normalizedBasicBarIndex(-1, 3))
        assertNull(normalizedBasicBarIndex(null, 3))
    }
}
