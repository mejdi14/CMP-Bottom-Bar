package basic.example.component.bottombar

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
import basic.example.component.data.BasicBarPosition
import org.example.core.bottombar.indicator.BottomBarSelectedIndicator
import org.example.core.bottombar.indicator.PositionType
import org.example.core.bottombar.indicator.ShapeType

@Composable
fun bottomBarIndicatorComposable(
    config: BottomBarSelectedIndicator,
    color: Color = Color.Blue,
    animatedOffset: State<Dp>,
    spaceBetween: Dp,
    selectedIndex: MutableState<Int>,
    basicBarPosition: BasicBarPosition
) {


    val shapeModifier = when (config.shapeType) {
        ShapeType.Square -> Modifier.size(
            config.size - config.padding,
            config.size - config.padding
        )

        ShapeType.Line -> Modifier.width(config.size - config.padding).height(config.thickness)
        ShapeType.Circle -> Modifier.size(config.size - config.padding).clip(CircleShape)
        ShapeType.Dot -> Modifier.size(config.thickness).clip(CircleShape)
    }

    Box(
        modifier = Modifier
            .offset(
                x = (animatedOffset.value + (spaceBetween * (selectedIndex.value + 1))),
                y = if ((config.shapeType == ShapeType.Line || config.shapeType == ShapeType.Dot)
                    && config.positionType == PositionType.Bottom
                )
                    0.dp + (config.size - config.thickness)
                else 0.dp + (config.padding / 2)
            )

            .background(color, RoundedCornerShape(10.dp))

            .then(shapeModifier)
    )
}
