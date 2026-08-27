package basic.mejdi14.component.bottombar

import androidx.compose.ui.unit.dp
import basic.mejdi14.component.bottombar.indicator.basicBarIndicatorLayout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape

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

    @Test
    fun lineAndDotIndicatorsShrinkTheItemInsideAFixedBarSlot() {
        val startLayout = basicBarIndicatorLayout(
            config = BottomBarIndicatorConfig(
                shapeType = BottomBarIndicatorShape.LINE,
                position = BottomBarIndicatorPosition.START,
            ),
            itemSize = 50.dp,
        )
        val endLayout = basicBarIndicatorLayout(
            config = BottomBarIndicatorConfig(
                shapeType = BottomBarIndicatorShape.DOT,
                position = BottomBarIndicatorPosition.END,
            ),
            itemSize = 50.dp,
        )

        assertEquals(50.dp, startLayout.slotSize)
        assertEquals(41.dp, startLayout.itemSize)
        assertEquals(9.dp, startLayout.itemCrossAxisOffset)
        assertEquals(0.dp, startLayout.indicatorCrossAxisOffset)
        assertEquals(50.dp, endLayout.slotSize)
        assertEquals(41.dp, endLayout.itemSize)
        assertEquals(0.dp, endLayout.itemCrossAxisOffset)
        assertEquals(44.dp, endLayout.indicatorCrossAxisOffset)
    }

    @Test
    fun filledIndicatorsRemainAlignedWithTheItem() {
        val layout = basicBarIndicatorLayout(
            config = BottomBarIndicatorConfig(
                shapeType = BottomBarIndicatorShape.CIRCLE,
                position = BottomBarIndicatorPosition.END,
                thickness = 6.dp,
                padding = 4.dp,
            ),
            itemSize = 50.dp,
        )

        assertEquals(50.dp, layout.slotSize)
        assertEquals(50.dp, layout.itemSize)
        assertEquals(0.dp, layout.itemCrossAxisOffset)
        assertEquals(0.dp, layout.indicatorCrossAxisOffset)
    }
}
