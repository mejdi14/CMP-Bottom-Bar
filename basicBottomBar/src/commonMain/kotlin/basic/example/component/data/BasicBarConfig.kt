package basic.example.component.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.data.BottomBarHoverText

data class BasicBarConfig (
    val itemsRadius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 8,
    val itemSeparationSpace: Dp = 10.dp,
    val parentAndSubVerticalSeparationSpace: Dp = 10.dp,
    val marginForScreenSizeChanges: Float = 10f,
    val hoverTextConfig: BottomBarHoverText = BottomBarHoverText()
)