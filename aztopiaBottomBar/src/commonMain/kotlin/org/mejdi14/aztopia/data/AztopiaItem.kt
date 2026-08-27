package org.mejdi14.aztopia.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.mejdi14.core.bottombar.interaction.BottomBarInteraction
import org.mejdi14.core.bottombar.listener.BottomBarClickListener

data class AztopiaItem(
    override val icon: BottomBarIcon,
    override val size: Dp = 50.dp,
    override val backgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = backgroundColor,
    override val shape: Shape = RoundedCornerShape(10.dp),
    override val interaction: BottomBarInteraction = BottomBarInteraction(),
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val itemSeparationSpace: Dp = 10.dp,
    val onClick: BottomBarClickListener<AztopiaItem> = BottomBarClickListener { _, _ -> },
) : BottomBarItem
