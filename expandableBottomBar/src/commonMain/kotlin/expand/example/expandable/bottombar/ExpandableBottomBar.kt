package expand.example.expandable.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.data.BottomBarItem
import org.example.core.bottombar.indicator.SelectedIndicatorConfig
import org.example.core.bottombar.indicator.ShapeType
import org.jetbrains.compose.resources.painterResource

@Composable
fun ExpandableBottomBar(bottomBarItems: List<BottomBarItem>, parentModifier : Modifier, onIconClick: (BottomBarItem) -> Unit) {
    val selectedIndex = remember { mutableStateOf(0) }
    val itemWidth = 50.dp

    val lazyListState = rememberLazyListState()

    var parentWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val bottomBarWidth = itemWidth * bottomBarItems.size + 10.dp

    val animatedOffset = animateDpAsState(
        targetValue = (selectedIndex.value * itemWidth.value).dp
    )
    Box(
        parentModifier.width(300.dp).padding(5.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
    ) {
        Column(Modifier.align(Alignment.Center)) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .onGloballyPositioned { layoutCoordinates ->
                        val widthPx = layoutCoordinates.size.width
                        parentWidth = with(density) { widthPx.toDp() }

                        animatedOffset.value
                    }
            ) {
                val spaceBetween = ((parentWidth - (itemWidth * 4)) / 5)
                CustomBottomBar(
                    config = SelectedIndicatorConfig(
                        shapeType = ShapeType.Line,
                    ),
                    spaceBetween = spaceBetween,
                    animatedOffset = animatedOffset,
                    selectedIndex = selectedIndex
                )
                LazyRow(
                    state = lazyListState,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(bottomBarItems) { index, item ->

                        IconButton(
                            onClick = {
                                selectedIndex.value = index
                                onIconClick(item)
                            },
                            modifier = Modifier.size(50.dp).align(Alignment.Center)
                        ) {
                            Icon(
                                painter = painterResource(item.icon.selectedIconDrawable),
                                contentDescription = item.contentDescription,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .onGloballyPositioned { layoutCoordinates ->
                        val widthPx = layoutCoordinates.size.width
                        parentWidth = with(density) { widthPx.toDp() }

                        animatedOffset.value
                    }

            ) {
                val spaceBetween = ((parentWidth - (itemWidth * 4)) / 5)
                CustomBottomBar(
                    config = SelectedIndicatorConfig(
                        shapeType = ShapeType.Line,
                    ),
                    spaceBetween = spaceBetween,
                    animatedOffset = animatedOffset,
                    selectedIndex = selectedIndex
                )
                LazyRow(
                    state = lazyListState,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(bottomBarItems) { index, item ->

                        IconButton(
                            onClick = {
                                selectedIndex.value = index
                                onIconClick(item)
                            },
                            modifier = Modifier.size(50.dp).align(Alignment.Center)
                        ) {
                            Icon(
                                painter = painterResource(item.icon.selectedIconDrawable),
                                contentDescription = item.contentDescription,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
