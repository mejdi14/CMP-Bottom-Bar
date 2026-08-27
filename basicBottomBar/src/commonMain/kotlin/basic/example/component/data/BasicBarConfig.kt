package basic.mejdi14.component.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig

data class BasicBarConfig(
    val itemSize: Dp = 50.dp,
    val outerPadding: Dp = 10.dp,
    val contentPadding: Dp = 5.dp,
    val itemSpacing: Dp = 10.dp,
    val iconStyle: BasicBarIconStyle? = null,
    val containerColor: Color = Color(0xFF1C2437),
    val hoverColor: Color = Color(0xFF293751),
    val indicator: BottomBarIndicatorConfig = BottomBarIndicatorConfig(),
    val shape: Shape = RoundedCornerShape(10.dp),
    val additionalItems: BasicBarAdditionalItems? = null,
    val position: BasicBarPosition = BasicBarPosition.HorizontalBottom,
    val hoverTextStyle: BasicBarHoverTextStyle = BasicBarHoverTextStyle(),
) {
    init {
        require(itemSize > 0.dp) { "itemSize must be greater than zero" }
        require(outerPadding >= 0.dp) { "outerPadding cannot be negative" }
        require(contentPadding >= 0.dp) { "contentPadding cannot be negative" }
        require(itemSpacing >= 0.dp) { "itemSpacing cannot be negative" }
    }
}
