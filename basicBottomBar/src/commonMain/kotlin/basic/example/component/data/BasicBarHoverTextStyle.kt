package basic.mejdi14.component.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class BasicBarHoverTextStyle(
    val containerModifier: Modifier = Modifier,
    val textModifier: Modifier = Modifier,
    val color: Color = Color.White,
    val style: TextStyle = TextStyle.Default,
)
