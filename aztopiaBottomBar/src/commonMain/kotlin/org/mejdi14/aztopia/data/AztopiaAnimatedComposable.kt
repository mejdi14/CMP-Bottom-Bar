package org.mejdi14.aztopia.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.aztopia.helper.AztopiaTrio
import org.mejdi14.core.bottombar.data.BottomBarIcon

data class AztopiaAnimatedComposable(
    val icon: BottomBarIcon,
    val size: Dp = 80.dp,
    val backgroundColor: Color = Color.Blue,
    val animatedCircleItems: AztopiaTrio<AztopiaAnimatedCircle>,
    val circularMovementRadius: Dp = 22.dp,
    val itemsOffsetOverlayFriction: Int = 5,
    val sizeDifference: Dp = 10.dp,
)
