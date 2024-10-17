package basic.example.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
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
internal fun VerticalBasicBar(
    parentModifier: Modifier,
    spaceBetween: MutableState<Dp>,
    hoverSelectedIndex: MutableState<Int>,
    itemWidth: Dp,
    bottomBarItems: List<BasicItem>,
    isHovered: MutableState<Boolean>,
    parentHeight: MutableState<Dp>,
    density: Density,
    animatedOffset: State<Dp>,
    selectedIndex: MutableState<Int>,
    basicBarConfig: BasicBarConfig,
    lazyListState: LazyListState,
    onIconClick: (BottomBarItem) -> Unit
) {
    Row(parentModifier) {
        if (basicBarConfig.basicBarPosition == BasicBarPosition.VERTICAL_RIGHT)
            Box(Modifier.height(300.dp).padding(5.dp)) {
                HoverDescriptionTextComposable(
                    spaceBetween.value,
                    hoverSelectedIndex,
                    itemWidth,
                    bottomBarItems,
                    isHovered,
                    basicBarConfig.basicBarPosition
                )
            }
        Box(
            parentModifier.height(400.dp).padding(5.dp).width(60.dp)
                .background(color = basicBarConfig.backgroundColor, shape = basicBarConfig.shape)
                .onGloballyPositioned { layoutCoordinates ->
                    val heightPx = layoutCoordinates.size.height
                    parentHeight.value = with(density) { heightPx.toDp() }
                }
        ) {
            spaceBetween.value =
                ((parentHeight.value - (itemWidth * bottomBarItems.size)) / (bottomBarItems.size + 1))
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
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
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
                                ) basicBarConfig.hoveredBackgroundColor
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
        if (basicBarConfig.basicBarPosition == BasicBarPosition.VERTICAL_LEFT)
            Box(Modifier.height(300.dp).padding(5.dp)) {
                HoverDescriptionTextComposable(
                    spaceBetween.value,
                    hoverSelectedIndex,
                    itemWidth,
                    bottomBarItems,
                    isHovered,
                    basicBarConfig.basicBarPosition
                )
            }
    }
}
