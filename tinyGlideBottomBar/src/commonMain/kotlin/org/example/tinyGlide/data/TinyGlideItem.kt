package org.example.tinyGlide.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.BottomBarIdentifier
import org.example.core.bottombar.BottomBarItem
import org.jetbrains.compose.resources.DrawableResource

data class TinyGlideItem(
    override val icon: BottomBarIcon,
    override val contentDescription: String,
    override val size: Dp = 50.dp,
    override val title: String = "options",
    override val backgroundColor: Color = Color.Blue,
    override val itemShape: Shape = RoundedCornerShape(10.dp),
    override var index: Int = -1,
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction : Float = 1.3f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 8,
    val itemSeparationSpace: Dp = 10.dp,
    val subTinyGlideItems : List<TinyGlideItem> = listOf(),
    var itemCoordinatesOffset : Offset? = null,
    val parentAndSubVerticalSeparationSpace : Dp = 10.dp,
    val marginForScreenSizeChanges : Float = 10f,
    var parentItemDynamicSize: MutableState<Dp> = mutableStateOf(size)

    ) : BottomBarItem()