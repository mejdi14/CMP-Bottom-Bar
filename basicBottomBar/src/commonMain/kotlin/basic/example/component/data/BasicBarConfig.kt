package basic.mejdi14.component.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig

data class BasicBarConfig(
    val itemSize: Dp = 50.dp,
    val itemsRadius: Dp = 10.dp,
    val aroundItemsPadding: Dp = 10.dp,
    val iconStyle: BasicBarIconStyle? = null,
    val basicBarPadding: Dp = 5.dp,
    val backgroundColor: Color = Color(0xFF1c2437),
    val hoveredBackgroundColor: Color = Color(0xFF293751),
    val selectedIndicatorConfig: BottomBarIndicatorConfig = BottomBarIndicatorConfig(),
    val shape: Shape = RoundedCornerShape(10.dp),
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 8,
    val spaceBetweenItems: Dp = 10.dp,
    val additionalItems: BasicBarAdditionalItems? = null,
    val basicBarPosition: BasicBarPosition = BasicBarPosition.HORIZONTAL_BOTTOM,
    val hoverTextStyle: BasicBarHoverTextStyle = BasicBarHoverTextStyle(),
)
