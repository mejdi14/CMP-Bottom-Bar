package org.mejdi14.figma.bottombar

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class FigmaBarConfigTest {
    @Test
    fun lightPaletteUsesFigmaBlue() {
        assertEquals(FigmaBarConfig().selectedColor, FigmaBarConfig.light().selectedColor)
    }

    @Test
    fun iconCannotExceedItemSize() {
        assertFailsWith<IllegalArgumentException> {
            FigmaBarConfig(itemSize = 20.dp, iconSize = 24.dp)
        }
    }

    @Test
    fun invalidSelectionIsClearedForRendering() {
        assertNull(normalizedFigmaBarIndex(index = 4, itemCount = 4))
    }

    @Test
    fun selectionsAreIndependentBetweenGroups() {
        val state = FigmaBarState(mapOf(0 to 1, 1 to 2))

        state.select(groupIndex = 0, itemIndex = 3)

        assertEquals(3, state.selectedIndex(0))
        assertEquals(2, state.selectedIndex(1))
    }
}
