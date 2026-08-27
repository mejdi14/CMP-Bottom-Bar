package basic.mejdi14.component.data

import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BasicBarHoverTextStyle(
    val containerModifier: Modifier = Modifier,
    val textModifier: Modifier = Modifier,
    val containerColor: Color = Color.Black,
    val contentColor: Color = Color.White,
    val shape: Shape = RoundedCornerShape(6.dp),
    val textStyle: TextStyle = TextStyle.Default,
    val contentPadding: Dp = 4.dp,
    val height: Dp = 30.dp,
    val gap: Dp = 4.dp,
) {
    init {
        require(contentPadding >= 0.dp) { "contentPadding cannot be negative" }
        require(height > 0.dp) { "height must be greater than zero" }
        require(gap >= 0.dp) { "gap cannot be negative" }
    }
}
