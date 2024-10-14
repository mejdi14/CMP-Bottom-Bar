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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basic.example.component.data.BasicItem
import org.example.core.bottombar.data.BottomBarItem

@Composable
internal fun HoverDescriptionTextComposable(
    spaceBetween: Dp,
    animatedOffset: State<Dp>,
    selectedIndex: MutableState<Int>,
    itemWidth: Dp,
    bottomBarItems: List<BasicItem>
) {
    var parentWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(modifier = Modifier.height(30.dp)
        .onGloballyPositioned { layoutCoordinates ->
            val widthPx = layoutCoordinates.size.width
            parentWidth = with(density) { widthPx.toDp() }
        }
        .offset(
            x = (animatedOffset.value + (spaceBetween * (selectedIndex.value + 1))) + (itemWidth / 2) - (parentWidth / 2),
        ).clip(RoundedCornerShape(6.dp)).background(color = Color.Black)
    ) {
        Text(bottomBarItems.get(selectedIndex.value).hoverText, color = Color.White, modifier = Modifier.align(Alignment.Center).padding(4.dp))
    }
}