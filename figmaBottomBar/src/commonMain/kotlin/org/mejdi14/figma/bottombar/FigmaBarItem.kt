package org.mejdi14.figma.bottombar

import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.mejdi14.core.bottombar.interaction.BottomBarInteraction
import org.mejdi14.core.bottombar.listener.BottomBarClickListener
import org.mejdi14.core.bottombar.listener.BottomBarHoverListener

data class FigmaBarItem(
    override val icon: BottomBarIcon,
    val hasMenu: Boolean = false,
    override val interaction: BottomBarInteraction = BottomBarInteraction(),
    val onClick: BottomBarClickListener<FigmaBarItem> = BottomBarClickListener { _, _ -> },
    val onHover: BottomBarHoverListener<FigmaBarItem> = BottomBarHoverListener { _, _ -> },
) : BottomBarItem

data class FigmaBarItemPosition(
    val groupIndex: Int,
    val itemIndex: Int,
    val flatIndex: Int,
)
