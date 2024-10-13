package basic.example.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.indicator.PositionType
import org.example.core.bottombar.indicator.ShapeType
import org.jetbrains.skia.skottie.Logger

@Composable
internal fun HoverDescriptionText(
    spaceBetween: Dp,
    animatedOffset: State<Dp>,
    selectedIndex: MutableState<Int>
) {

    Box(modifier = Modifier.height(30.dp)
        .offset(
            x = (animatedOffset.value + (spaceBetween * (selectedIndex.value + 1))),
        ).clip(RoundedCornerShape(6.dp)).background(color = Color.Black)
    ) {
        Text("Host", color = Color.White, modifier = Modifier.align(Alignment.Center).padding(4.dp))
    }
}