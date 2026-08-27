package basic.mejdi14.component.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.mejdi14.core.bottombar.interaction.BottomBarInteraction
import org.mejdi14.core.bottombar.listener.BottomBarClickListener
import org.mejdi14.core.bottombar.listener.BottomBarHoverListener

data class BasicItem(
    override val icon: BottomBarIcon,
    override val size: Dp = 50.dp,
    override val backgroundColor: Color = Color.Transparent,
    override val selectedBackgroundColor: Color = backgroundColor,
    override val shape: Shape = RoundedCornerShape(10.dp),
    override val interaction: BottomBarInteraction = BottomBarInteraction(),
    val hoverText: String? = null,
    val onClick: BottomBarClickListener<BasicItem> = BottomBarClickListener { _, _ -> },
    val onHover: BottomBarHoverListener<BasicItem> = BottomBarHoverListener { _, _ -> },
) : BottomBarItem
