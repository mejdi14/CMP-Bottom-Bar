package expand.mejdi14.expandable.bottombar

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ExpandableBottomBarTest {
    @Test
    fun rowsDistributeItemsWithoutDuplication() {
        val rows = expandableRows((0..5).toList(), 2)

        assertEquals(listOf(3, 3), rows.map { it.size })
        assertEquals((0..5).toList(), rows.flatten().map { it.value })
        assertEquals((0..5).toList(), rows.flatten().map { it.index })
    }

    @Test
    fun unevenRowsPlaceExtraItemsFirst() {
        val rows = expandableRows((0..5).toList(), 4)

        assertEquals(listOf(2, 2, 1, 1), rows.map { it.size })
    }

    @Test
    fun rowCountCannotCreateEmptyRows() {
        val rows = expandableRows(listOf("a", "b"), 5)

        assertEquals(listOf(1, 1), rows.map { it.size })
    }

    @Test
    fun emptyItemsCreateNoRows() {
        assertEquals(emptyList(), expandableRows<Int>(emptyList(), 3))
    }

    @Test
    fun widthMustLeaveRoomForContent() {
        assertFailsWith<IllegalArgumentException> {
            ExpandableBarConfig(width = 10.dp, outerPadding = 5.dp)
        }
    }
}
