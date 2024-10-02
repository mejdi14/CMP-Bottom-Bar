package org.example.aztopia.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.aztopia.helper.AztopiaTrio
import org.example.core.bottombar.listener.ClickActionListener
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.BottomBarItem
import org.example.core.bottombar.BottomBarTitle

data class AztopiaAnimatedComposable (
    override val icon: BottomBarIcon,
    override val contentDescription: String,
    override val size: Dp = 80.dp,
    override val title: BottomBarTitle = BottomBarTitle("placeholder"),
    override val backgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = backgroundColor,
    override val itemShape: Shape = RoundedCornerShape(10.dp),
    override var index: Int = -1,
    val animatedCircleItems: AztopiaTrio<AztopiaAnimatedCircle>,
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val sizeDifference: Dp = 10.dp,
    val clickActionListener: ClickActionListener = EmptyClickActionListener
) : BottomBarItem(){
    override val disableClickIfAlreadySelected: Boolean = true
    override val withTitleShown: Boolean = false
}

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