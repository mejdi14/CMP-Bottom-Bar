package org.mejdi14.core.bottombar.interaction

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BottomBarInteractionTest {
    @Test
    fun selectModeSelectsClickedItem() {
        val interaction = BottomBarInteraction()

        assertEquals(2, interaction.nextSelectedIndex(currentIndex = 0, clickedIndex = 2))
    }

    @Test
    fun toggleModeDeselectsSelectedItem() {
        val interaction = BottomBarInteraction(selectionMode = BottomBarSelectionMode.TOGGLE)

        assertNull(interaction.nextSelectedIndex(currentIndex = 2, clickedIndex = 2))
        assertEquals(3, interaction.nextSelectedIndex(currentIndex = 2, clickedIndex = 3))
    }

    @Test
    fun noneModePreservesSelection() {
        val interaction = BottomBarInteraction(selectionMode = BottomBarSelectionMode.NONE)

        assertEquals(1, interaction.nextSelectedIndex(currentIndex = 1, clickedIndex = 3))
    }

    @Test
    fun disabledItemPreservesSelectionAndDoesNotDispatch() {
        val interaction = BottomBarInteraction(enabled = false, dispatchClickWhenSelected = true)

        assertEquals(1, interaction.nextSelectedIndex(currentIndex = 1, clickedIndex = 2))
        assertFalse(interaction.shouldDispatchClick(currentIndex = 1, clickedIndex = 2))
    }

    @Test
    fun selectedItemDispatchIsExplicit() {
        val defaultInteraction = BottomBarInteraction()
        val reselectableInteraction = BottomBarInteraction(dispatchClickWhenSelected = true)

        assertFalse(defaultInteraction.shouldDispatchClick(currentIndex = 1, clickedIndex = 1))
        assertTrue(reselectableInteraction.shouldDispatchClick(currentIndex = 1, clickedIndex = 1))
    }
}
