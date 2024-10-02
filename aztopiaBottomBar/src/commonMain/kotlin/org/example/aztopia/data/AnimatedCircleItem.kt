package org.example.aztopia.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.listener.ClickActionListener
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.BottomBarItem
import org.example.core.bottombar.BottomBarTitle

data class AnimatedCircleItem (
    override val icon: BottomBarIcon,
    override val contentDescription: String,
    override val size: Dp = 50.dp,
    override val title: BottomBarTitle = BottomBarTitle("placeholder"),
    override val unselectedBackgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = unselectedBackgroundColor,
    override val itemShape: Shape = RoundedCornerShape(10.dp),
    override var index: Int = -1,
    override val disableClickIfAlreadySelected: Boolean = true,
    override val withTitleShown: Boolean = false,
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val itemSeparationSpace: Dp = 10.dp,
    val clickActionListener: ClickActionListener = EmptyClickActionListener
) : BottomBarItem()

fun AztopiaItem.isSelectedItem(selectedItem: AztopiaItem?): Boolean {
    return this == selectedItem
}


val EmptyClickActionListener =

    object : ClickActionListener {
        override fun onItemClickListener(
        ) {
            // Do nothing
        }
    }