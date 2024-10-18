package expand.example.expandable.bottombar

import  androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.indicator.SelectedIndicatorConfig
import org.example.core.bottombar.indicator.BasicIndicatorShapeType

@Composable
fun CustomBottomBar(
    config: SelectedIndicatorConfig,
    color: Color = Color.Blue,
    animatedOffset: State<Dp>,
    spaceBetween: Dp,
    selectedIndex: MutableState<Int>
) {

    val shapeModifier = when (config.shapeType) {
        BasicIndicatorShapeType.Square -> Modifier.size(
            config.size - config.padding,
            config.size - config.padding
        )

        BasicIndicatorShapeType.Line -> Modifier.width(config.size - config.padding).height(config.thickness)
        BasicIndicatorShapeType.Circle -> Modifier.size(config.size - config.padding).clip(CircleShape)
        BasicIndicatorShapeType.Dot -> Modifier.size(config.thickness).clip(CircleShape)
    }

    Box(
        modifier = Modifier
            .offset(
                x = (animatedOffset.value + (spaceBetween * (selectedIndex.value + 1))),
                y = 0.dp
            )
            .then(shapeModifier)
            .background(color, RoundedCornerShape(10.dp))
    )
}
