package org.mejdi14.figma.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class FigmaBarState(initialSelectedIndices: Map<Int, Int> = mapOf(0 to 0)) {
    var selectedIndices by mutableStateOf(initialSelectedIndices)
        private set

    var hoveredIndex by mutableStateOf<Int?>(null)
        internal set

    fun selectedIndex(groupIndex: Int): Int? = selectedIndices[groupIndex]

    fun select(groupIndex: Int, itemIndex: Int?) {
        selectedIndices = if (itemIndex == null) {
            selectedIndices - groupIndex
        } else {
            selectedIndices + (groupIndex to itemIndex)
        }
    }
}

@Composable
fun rememberFigmaBarState(
    initialSelectedIndices: Map<Int, Int> = mapOf(0 to 0),
): FigmaBarState = remember { FigmaBarState(initialSelectedIndices) }
