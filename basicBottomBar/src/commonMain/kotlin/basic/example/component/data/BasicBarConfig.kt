package basic.example.component.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.data.BottomBarHoverText

data class BasicBarConfig (
    val itemsRadius: Dp = 10.dp,
    val backgroundColor: Color = Color(0xFF1c2437),
    val hoveredBackgroundColor: Color = Color(0xFF293751),
    val shape: Shape = RoundedCornerShape(10.dp),
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 8,
    val basicBarPosition: BasicBarPosition = BasicBarPosition.HORIZONTAL_BOTTOM,
    val hoverTextConfig: BottomBarHoverText = BottomBarHoverText()
)