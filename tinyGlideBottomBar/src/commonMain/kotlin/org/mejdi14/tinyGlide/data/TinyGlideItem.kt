package org.mejdi14.tinyGlide.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.mejdi14.core.bottombar.interaction.BottomBarInteraction
import org.mejdi14.core.bottombar.interaction.BottomBarSelectionMode
import org.mejdi14.core.bottombar.listener.BottomBarClickListener
import org.mejdi14.core.bottombar.listener.BottomBarHoverListener

@Immutable
data class TinyGlideItem(
    override val icon: BottomBarIcon,
    override val size: Dp = 50.dp,
    override val backgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = backgroundColor,
    override val shape: Shape = RoundedCornerShape(10.dp),
    override val interaction: BottomBarInteraction = BottomBarInteraction(
        selectionMode = BottomBarSelectionMode.TOGGLE,
        dispatchClickWhenSelected = true,
    ),
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.2f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 220,
    val itemSeparationSpace: Dp = 10.dp,
    val subTinyGlideItems: List<TinyGlideItem> = emptyList(),
    val parentAndSubVerticalSeparationSpace: Dp = 10.dp,
    val marginForScreenSizeChanges: Float = 10f,
    val onHover: BottomBarHoverListener<TinyGlideItem> = BottomBarHoverListener { _, _ -> },
    val onClick: BottomBarClickListener<TinyGlideItem> = BottomBarClickListener { _, _ -> },
    val key: String = "${icon.resource.hashCode()}:${icon.contentDescription.orEmpty()}",
    val decoration: TinyGlideItemDecoration = TinyGlideItemDecoration(),
    val animation: TinyGlideAnimationConfig = TinyGlideAnimationConfig(
        parentHoverScale = onSelectItemSizeChangeFriction,
        parentSelectedScale = onSelectItemSizeChangeFriction,
        childHoverScale = onSelectItemSizeChangeFriction,
        parentHoverDurationMillis = onSelectItemSizeChangeDurationMillis,
        parentSelectionDurationMillis = onSelectItemSizeChangeDurationMillis,
        childHoverDurationMillis = onSelectItemSizeChangeDurationMillis,
    ),
) : BottomBarItem

fun TinyGlideItem.isSelectedItem(selectedItem: TinyGlideItem?): Boolean {
    return key == selectedItem?.key
}
