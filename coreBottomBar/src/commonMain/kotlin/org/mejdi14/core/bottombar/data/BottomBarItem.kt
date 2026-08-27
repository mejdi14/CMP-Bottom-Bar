package org.mejdi14.core.bottombar.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.interaction.BottomBarInteraction

/**
 * Visual and interaction properties shared by selectable bottom-bar items.
 *
 * Runtime state such as an item's index or whether it is selected belongs to the
 * composable displaying the item, not to the item model itself.
 */
interface BottomBarItem {
    val icon: BottomBarIcon
    val size: Dp
        get() = 50.dp
    val backgroundColor: Color
        get() = Color.Transparent
    val selectedBackgroundColor: Color
        get() = backgroundColor
    val shape: Shape
        get() = RoundedCornerShape(10.dp)
    val interaction: BottomBarInteraction
        get() = BottomBarInteraction()
}
