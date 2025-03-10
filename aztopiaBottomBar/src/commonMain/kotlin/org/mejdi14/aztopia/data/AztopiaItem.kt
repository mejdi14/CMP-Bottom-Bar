package org.mejdi14.aztopia.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.mejdi14.core.bottombar.data.BottomBarTitle
import org.mejdi14.core.bottombar.listener.ClickActionListener
import org.mejdi14.core.bottombar.listener.EmptyClickActionListener
import org.mejdi14.core.bottombar.listener.HoverActionListener
import org.mejdi14.core.bottombar.listener.emptyHoverActionListener

data class AztopiaItem(
    override val icon: BottomBarIcon,
    override val contentDescription: String,
    override val size: Dp = 50.dp,
    override val title: BottomBarTitle = BottomBarTitle("placeholder"),
    override val backgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = backgroundColor,
    override val itemShape: Shape = RoundedCornerShape(10.dp),
    override var index: Int = -1,
    override val disableClickIfAlreadySelected: Boolean = true,
    override val withTitleShown: Boolean = false,
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val itemSeparationSpace: Dp = 10.dp,
    val hoverActionListener: HoverActionListener<AztopiaItem> = emptyHoverActionListener(),
    val clickActionListener: ClickActionListener = EmptyClickActionListener
) : BottomBarItem() {
    override val OnItemClick: Boolean
        get() = TODO("Not yet implemented")
}
