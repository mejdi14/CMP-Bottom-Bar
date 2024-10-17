package basic.example.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
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
import org.example.core.bottombar.indicator.SelectedIndicatorConfig
import org.example.core.bottombar.indicator.PositionType
import org.example.core.bottombar.indicator.ShapeType
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun HorizontalBasicBar(
    parentModifier: Modifier,
    spaceBetween: MutableState<Dp>,
    hoverSelectedIndex: MutableState<Int>,
    bottomBarItems: List<BasicItem>,
    isHovered: MutableState<Boolean>,
    parentWidth: MutableState<Dp>,
    density: Density,
    animatedOffset: State<Dp>,
    selectedIndex: MutableState<Int>,
    basicBarConfig: BasicBarConfig,
    lazyListState: LazyListState,
    onIconClick: (BottomBarItem) -> Unit
) {
    Column(parentModifier.padding(horizontal = 50.dp)) {
        if (basicBarConfig.basicBarPosition == BasicBarPosition.HORIZONTAL_BOTTOM)
        Box(Modifier.width(300.dp).padding(5.dp)) {
            HoverDescriptionTextComposable(
                spaceBetween.value,
                hoverSelectedIndex,
                bottomBarItems,
                isHovered,
                basicBarConfig
            )
        }
        Box(
            Modifier.width(400.dp).padding(basicBarConfig.basicBarPadding).height(basicBarConfig.itemSize + (basicBarConfig.basicBarPadding * 2))
                .background(color = basicBarConfig.backgroundColor, shape = basicBarConfig.shape)
                .onGloballyPositioned { layoutCoordinates ->
                    val widthPx = layoutCoordinates.size.width
                    parentWidth.value = with(density) { widthPx.toDp() }
                }
        ) {
            spaceBetween.value = ((parentWidth.value - (basicBarConfig.itemSize * (bottomBarItems.size))) / (bottomBarItems.size + 1))
            bottomBarIndicatorComposable(
                config = SelectedIndicatorConfig(
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
                modifier = Modifier.fillMaxHeight().fillMaxWidth()
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
        if (basicBarConfig.basicBarPosition == BasicBarPosition.HORIZONTAL_TOP)
            Box(Modifier.width(300.dp).padding(5.dp)) {
                HoverDescriptionTextComposable(
                    spaceBetween.value,
                    hoverSelectedIndex,
                    bottomBarItems,
                    isHovered,
                    basicBarConfig
                )
            }
    }
}
