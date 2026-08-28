package org.mejdi14.tinyGlide.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
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
    val onSelectItemSizeChangeFriction: Float = 1f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 220,
    val itemSeparationSpace: Dp = 10.dp,
    val subTinyGlideItems: List<TinyGlideItem> = listOf(),
    var itemCoordinatesOffset: Offset? = null,
    val parentAndSubVerticalSeparationSpace: Dp = 10.dp,
    val marginForScreenSizeChanges: Float = 10f,
    var parentItemDynamicSize: MutableState<Dp> = mutableStateOf(size),
    val onHover: BottomBarHoverListener<TinyGlideItem> = BottomBarHoverListener { _, _ -> },
    val onClick: BottomBarClickListener<TinyGlideItem> = BottomBarClickListener { _, _ -> },
) : BottomBarItem

fun TinyGlideItem.isSelectedItem(selectedItem: TinyGlideItem?): Boolean {
    return this == selectedItem
}
