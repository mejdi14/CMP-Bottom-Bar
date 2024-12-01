package basic.example.component.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basic.example.component.bottombar.hover.HoverDescriptionTextComposable
import basic.example.component.bottombar.icon.BasicBarIconComposable
import basic.example.component.bottombar.indicator.bottomBarIndicatorComposable
import basic.example.component.data.BasicBarConfig
import basic.example.component.data.BasicBarPosition
import basic.example.component.data.BasicItem
import org.example.core.bottombar.data.BottomBarItem
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
    Column(parentModifier.fillMaxWidth().padding(horizontal = 10.dp)) {
        if (basicBarConfig.basicBarPosition == BasicBarPosition.HORIZONTAL_BOTTOM)
            Box(Modifier.padding(5.dp)) {
                HoverDescriptionTextComposable(
                    spaceBetween.value,
                    hoverSelectedIndex,
                    bottomBarItems,
                    isHovered,
                    basicBarConfig
                )
            }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(basicBarConfig.additionalItems?.leftTopItem != null) {
                Box(
                    Modifier.size(basicBarConfig.itemSize)
                        .background(
                            Color.White,
                            shape = basicBarConfig.additionalItems.leftTopItem?.itemShape
                                ?: RoundedCornerShape(10.dp)
                        )
                ) {
                    val currentAdditionalIcon = basicBarConfig.additionalItems.leftTopItem?.icon
                    if (currentAdditionalIcon != null) {
                        Icon(
                            painter = painterResource(currentAdditionalIcon.iconDrawable),
                            contentDescription = currentAdditionalIcon.contentDescription,
                            Modifier.align(Alignment.Center),
                        )
                    }
                }
            } else if(basicBarConfig.additionalItems?.rightBottomItem != null){
                Spacer(Modifier.width(basicBarConfig.itemSize))
            }

            Box(
                Modifier.padding(basicBarConfig.basicBarPadding)
                    .height(basicBarConfig.itemSize)
                    .background(
                        color = basicBarConfig.backgroundColor,
                        shape = basicBarConfig.shape
                    )
                    .onGloballyPositioned { layoutCoordinates ->
                        val widthPx = layoutCoordinates.size.width
                        parentWidth.value = with(density) { widthPx.toDp() }
                    }
            ) {
                spaceBetween.value =
                    ((parentWidth.value - (basicBarConfig.itemSize * (bottomBarItems.size))) / (bottomBarItems.size + 1))
                bottomBarIndicatorComposable(
                    config = basicBarConfig.selectedIndicatorConfig,
                    spaceBetween = spaceBetween.value,
                    animatedOffset = animatedOffset,
                    selectedIndex = selectedIndex,
                    basicBarPosition = basicBarConfig.basicBarPosition,
                    itemSize = basicBarConfig.itemSize
                )
                LazyRow(
                    state = lazyListState,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxHeight()
                        .width((basicBarConfig.itemSize * bottomBarItems.size) + (basicBarConfig.spaceBetweenItems * (bottomBarItems.size - 1)))
                ) {
                    itemsIndexed(bottomBarItems) { index, item ->

                        Box(
                            modifier = Modifier.size(basicBarConfig.itemSize)
                                .align(Alignment.Center)
                                .hoverEffect { onHover ->
                                    isHovered.value = onHover
                                    hoverSelectedIndex.value = index
                                }
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    selectedIndex.value = index
                                    onIconClick(item)
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
                            BasicBarIconComposable(
                                basicBarConfig.globalBasicIconConfig,
                                item,
                                Modifier.align(Alignment.Center).size(item.size),
                                selectedIndex.value == index
                            )
                        }
                    }
                }
            }
            if(basicBarConfig.additionalItems?.rightBottomItem != null) {
                Box(
                    Modifier.size(basicBarConfig.itemSize)
                        .background(
                            Color.White,
                            shape = basicBarConfig.additionalItems.rightBottomItem?.itemShape
                                ?: RoundedCornerShape(10.dp)
                        )
                ) {
                    val currentAdditionalIcon =
                        basicBarConfig.additionalItems.rightBottomItem?.icon
                    if (currentAdditionalIcon != null) {
                        Icon(
                            painter = painterResource(currentAdditionalIcon.iconDrawable),
                            contentDescription = currentAdditionalIcon.contentDescription,
                            Modifier.align(Alignment.Center),
                        )
                    }
                }
            } else if(basicBarConfig.additionalItems?.leftTopItem != null){
                Spacer(Modifier.width(basicBarConfig.itemSize))
            }
        }
        if (basicBarConfig.basicBarPosition == BasicBarPosition.HORIZONTAL_TOP)
            Box(Modifier.padding(5.dp)) {
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


