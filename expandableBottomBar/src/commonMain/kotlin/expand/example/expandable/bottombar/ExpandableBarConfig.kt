package expand.mejdi14.expandable.bottombar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape

data class ExpandableBarConfig(
    val width: Dp = 300.dp,
    val rowHeight: Dp = 60.dp,
    val itemSize: Dp = 50.dp,
    val rowCount: Int = 1,
    val outerPadding: Dp = 5.dp,
    val containerColor: Color = Color.Black,
    val iconColor: Color = Color.White,
    val shape: Shape = RoundedCornerShape(10.dp),
    val indicator: BottomBarIndicatorConfig = BottomBarIndicatorConfig(
        color = Color.White,
        shapeType = BottomBarIndicatorShape.LINE,
        thickness = 3.dp,
    ),
) {
    init {
        require(width > 0.dp) { "width must be greater than zero" }
        require(rowHeight > 0.dp) { "rowHeight must be greater than zero" }
        require(itemSize > 0.dp) { "itemSize must be greater than zero" }
        require(rowCount > 0) { "rowCount must be greater than zero" }
        require(outerPadding >= 0.dp) { "outerPadding cannot be negative" }
        require(width > outerPadding * 2) { "width must be greater than its horizontal padding" }
        require(indicator.padding >= 0.dp) { "indicator padding cannot be negative" }
        require(indicator.thickness > 0.dp) { "indicator thickness must be greater than zero" }
        require(indicator.padding < itemSize) { "indicator padding must be smaller than itemSize" }
    }
}
