package org.example.aztopia.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.aztopia.listeners.ClickActionListener
import org.example.aztopia.listeners.HoverActionListener
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.BottomBarItem

data class AztopiaItem(
    override val icon: BottomBarIcon,
    override val contentDescription: String,
    override val size: Dp = 50.dp,
    override val title: String = "options",
    override val unselectedBackgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = unselectedBackgroundColor,
    override val itemShape: Shape = RoundedCornerShape(10.dp),
    override var index: Int = -1,
    override val disableClickIfAlreadySelected: Boolean = true,
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 8,
    val itemSeparationSpace: Dp = 10.dp,
    val subAztopiaItems: List<AztopiaItem> = listOf(),
    var itemCoordinatesOffset: Offset? = null,
    val parentAndSubVerticalSeparationSpace: Dp = 10.dp,
    val marginForScreenSizeChanges: Float = 10f,
    var parentItemDynamicSize: MutableState<Dp> = mutableStateOf(size),
    val hoverActionListener: HoverActionListener = EmptyHoverActionListener,
    val clickActionListener: ClickActionListener = EmptyClickActionListener
) : BottomBarItem()

fun AztopiaItem.isSelectedItem(selectedItem: AztopiaItem?): Boolean {
    return this == selectedItem
}

val EmptyHoverActionListener = object : HoverActionListener {
    override fun onHoverEnter(aztopiaItem: AztopiaItem) {
        // Do nothing
    }

    override fun onHoverExit(aztopiaItem: AztopiaItem) {
        // Do nothing
    }

    override fun onHoverParentItem(aztopiaItem: AztopiaItem) {
        // Do nothing
    }

    override fun onHoverSubItem(aztopiaItem: AztopiaItem) {
        // Do nothing
    }
}

val EmptyClickActionListener =

    object : ClickActionListener {
        override fun onItemClickListener(
        ) {
            // Do nothing
        }
    }