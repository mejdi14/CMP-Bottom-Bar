package basic.example.component.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basic.example.component.data.BasicBarConfig
import basic.example.component.data.BasicBarPosition
import basic.example.component.data.BasicItem
import org.example.core.bottombar.data.BottomBarItem
import org.example.core.bottombar.indicator.BottomBarSelectedIndicator
import org.example.core.bottombar.indicator.PositionType
import org.example.core.bottombar.indicator.ShapeType
import org.jetbrains.compose.resources.painterResource

@Composable
fun BasicBottomBar(
    bottomBarItems: List<BasicItem>,
    basicBarConfig: BasicBarConfig,
    parentModifier: Modifier,
    onIconClick: (BottomBarItem) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val hoverSelectedIndex = remember { mutableStateOf(0) }
    val itemWidth = 50.dp

    val lazyListState = rememberLazyListState()

    var parentWidth = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val bottomBarWidth = itemWidth * bottomBarItems.size + 10.dp
    val isHovered = remember { mutableStateOf(false) }
    val animatedOffset = animateDpAsState(
        targetValue = (selectedIndex.value * itemWidth.value).dp
    )
    var spaceBetween = remember { mutableStateOf(0.dp) }
    when(basicBarConfig.basicBarPosition){
        BasicBarPosition.HORIZONTAL_BOTTOM, BasicBarPosition.HORIZONTAL_TOP -> {
            Column(parentModifier) {
                Box(Modifier.width(300.dp).padding(5.dp)) {
                    HoverDescriptionTextComposable(
                        spaceBetween.value,
                        hoverSelectedIndex,
                        itemWidth,
                        bottomBarItems,
                        isHovered
                    )
                }
                Box(
                    parentModifier.width(300.dp).padding(5.dp).height(60.dp)
                        .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
                        .onGloballyPositioned { layoutCoordinates ->
                            val widthPx = layoutCoordinates.size.width
                            parentWidth.value = with(density) { widthPx.toDp() }
                        }
                ) {
                    spaceBetween.value = ((parentWidth.value - (itemWidth * 4)) / 5)
                    bottomBarIndicatorComposable(
                        config = BottomBarSelectedIndicator(
                            shapeType = ShapeType.Square,
                            positionType = PositionType.Bottom
                        ),
                        spaceBetween = spaceBetween.value,
                        animatedOffset = animatedOffset,
                        selectedIndex = selectedIndex,
                        basicBarPosition = basicBarConfig.basicBarPosition
                    )
                    LazyRow(
                        state = lazyListState,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(bottomBarItems) { index, item ->

                            IconButton(
                                onClick = {
                                    selectedIndex.value = index
                                    onIconClick(item)
                                },
                                modifier = Modifier.align(Alignment.Center)
                                    .hoverEffect { onHover ->
                                        isHovered.value = onHover
                                        hoverSelectedIndex.value = index
                                    }
                                    .background(
                                        color = if (isHovered.value
                                            && index == hoverSelectedIndex.value
                                            && index != selectedIndex.value
                                        ) Color.Gray
                                        else
                                            Color.Unspecified,
                                        RoundedCornerShape(10.dp)
                                    )
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
        BasicBarPosition.VERTICAL_LEFT, BasicBarPosition.VERTICAL_RIGHT -> {
            VerticalBasicBottomBar(
                parentModifier,
                spaceBetween,
                hoverSelectedIndex,
                itemWidth,
                bottomBarItems,
                isHovered,
                parentWidth,
                density,
                animatedOffset,
                selectedIndex,
                basicBarConfig,
                lazyListState,
                onIconClick
            )

        }
    }
}
