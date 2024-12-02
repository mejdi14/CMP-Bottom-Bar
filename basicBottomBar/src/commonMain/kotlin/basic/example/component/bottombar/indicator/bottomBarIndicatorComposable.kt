package basic.example.component.bottombar.indicator

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basic.example.component.data.BasicBarPosition
import org.example.core.bottombar.indicator.BasicIndicatorShapeType
import org.example.core.bottombar.indicator.PositionType
import org.example.core.bottombar.indicator.SelectedIndicatorConfig

@Composable
internal fun bottomBarIndicatorComposable(
    config: SelectedIndicatorConfig,
    animatedOffset: State<Dp>,
    spaceBetween: Dp,
    selectedIndex: MutableState<Int>,
    basicBarPosition: BasicBarPosition,
    itemSize: Dp
) {
    val shapeModifier = when (config.shapeType) {
        BasicIndicatorShapeType.Square -> Modifier.size(
            config.size - config.padding,
            config.size - config.padding
        ).background(color = config.color, shape = RoundedCornerShape(10.dp))

        BasicIndicatorShapeType.Line -> Modifier.width(config.size - config.padding)
            .height(config.thickness).background(color = config.color)

        BasicIndicatorShapeType.Circle -> Modifier.size(config.size - config.padding)
            .clip(CircleShape).background(color = config.color)

        BasicIndicatorShapeType.Dot -> Modifier.size(config.thickness).clip(CircleShape)
            .background(color = config.color)
    }

    when (basicBarPosition) {
        BasicBarPosition.HORIZONTAL_BOTTOM, BasicBarPosition.HORIZONTAL_TOP -> {
            Box(
                modifier = Modifier
                    .offset(
                        x = (animatedOffset.value + (spaceBetween * (selectedIndex.value + 1))) + if (config.shapeType == BasicIndicatorShapeType.Dot) ((itemSize / 2) - (config.thickness / 2)) else 0.dp,
                        y = if ((config.shapeType == BasicIndicatorShapeType.Line || config.shapeType == BasicIndicatorShapeType.Dot)
                            && config.positionType == PositionType.Bottom
                        )
                            0.dp + (config.size - config.thickness) - (if (config.shapeType == BasicIndicatorShapeType.Dot) config.thickness else 0.dp)
                        else 0.dp + (config.padding / 2)
                    )
                    .then(shapeModifier)
            )
        }

        BasicBarPosition.VERTICAL_LEFT, BasicBarPosition.VERTICAL_RIGHT -> {
            Box(
                modifier = Modifier
                    .offset(
                        y = (animatedOffset.value + (spaceBetween * (selectedIndex.value + 1))),
                        x = if ((config.shapeType == BasicIndicatorShapeType.Line || config.shapeType == BasicIndicatorShapeType.Dot)
                            && config.positionType == PositionType.Bottom
                        )
                            0.dp + (config.size - config.thickness)
                        else 0.dp + (config.padding / 2)
                    )
                    .then(shapeModifier)
            )
        }
    }

}
