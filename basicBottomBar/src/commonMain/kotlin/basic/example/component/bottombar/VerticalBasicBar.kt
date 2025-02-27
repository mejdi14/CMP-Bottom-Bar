package basic.mejdi14.component.bottombar

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import basic.example.component.bottombar.hover.HoverContainerComposable
import basic.mejdi14.component.bottombar.hover.HoverDescriptionTextComposable
import basic.mejdi14.component.bottombar.icon.BasicBarIconComposable
import basic.mejdi14.component.bottombar.indicator.bottomBarIndicatorComposable
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarPosition
import basic.mejdi14.component.data.BasicItem
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun VerticalBasicBar(
    parentModifier: Modifier,
    spaceBetween: MutableState<Dp>,
    hoverSelectedIndex: MutableState<Int>,
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
            HoverContainerComposable(basicBarConfig, spaceBetween, hoverSelectedIndex, bottomBarItems, isHovered)
        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (basicBarConfig.additionalItems?.leftTopItem != null) {
                Column {
                    Box(
                        Modifier.size(basicBarConfig.itemSize)
                            .clickable {
                                (basicBarConfig.additionalItems.leftTopItem as BasicItem).clickActionListener.onItemClickListener()
                            }
                            .background(
                                basicBarConfig.additionalItems.leftTopItem?.backgroundColor
                                    ?: Color.White,
                                shape = basicBarConfig.additionalItems.leftTopItem?.itemShape
                                    ?: RoundedCornerShape(10.dp)
                            )
                    ) {
                        val currentAdditionalIcon =
                            basicBarConfig.additionalItems.leftTopItem?.icon
                        if (currentAdditionalIcon != null) {
                            androidx.compose.material.Icon(
                                painter = painterResource(currentAdditionalIcon.iconDrawable),
                                contentDescription = currentAdditionalIcon.contentDescription,
                                Modifier.align(Alignment.Center),
                            )
                        }
                    }
                    Spacer(Modifier.height(basicBarConfig.spaceBetweenItems))
                }
            } else if (basicBarConfig.additionalItems?.rightBottomItem != null) {
                Spacer(Modifier.height(basicBarConfig.itemSize + basicBarConfig.spaceBetweenItems))
            }
            Box(
                parentModifier.padding(basicBarConfig.basicBarPadding)
                    .width(basicBarConfig.itemSize + (basicBarConfig.basicBarPadding * 2))
                    .background(
                        color = basicBarConfig.backgroundColor,
                        shape = basicBarConfig.shape
                    )
                    .onGloballyPositioned { layoutCoordinates ->
                        val heightPx = layoutCoordinates.size.height
                        parentHeight.value = with(density) { heightPx.toDp() }
                    }
            ) {
                spaceBetween.value =
                    ((parentHeight.value - (basicBarConfig.itemSize * bottomBarItems.size)) / (bottomBarItems.size + 1))
                bottomBarIndicatorComposable(
                    config = basicBarConfig.selectedIndicatorConfig,
                    spaceBetween = spaceBetween.value,
                    animatedOffset = animatedOffset,
                    selectedIndex = selectedIndex,
                    basicBarPosition = basicBarConfig.basicBarPosition,
                    itemSize = basicBarConfig.itemSize
                )
                LazyColumn(
                    state = lazyListState,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                        .height((basicBarConfig.itemSize * bottomBarItems.size) + (basicBarConfig.spaceBetweenItems * (bottomBarItems.size - 1)))
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
            if (basicBarConfig.additionalItems?.rightBottomItem != null) {
                Column {
                    Spacer(Modifier.height(basicBarConfig.spaceBetweenItems))
                    Box(
                        Modifier.size(basicBarConfig.itemSize)
                            .clickable {
                                (basicBarConfig.additionalItems.rightBottomItem as BasicItem).clickActionListener.onItemClickListener()
                            }
                            .background(
                                basicBarConfig.additionalItems.rightBottomItem?.backgroundColor
                                    ?: Color.White,
                                shape = basicBarConfig.additionalItems.rightBottomItem?.itemShape
                                    ?: RoundedCornerShape(10.dp)
                            )
                    ) {
                        val currentAdditionalIcon =
                            basicBarConfig.additionalItems.rightBottomItem?.icon
                        if (currentAdditionalIcon != null) {
                            androidx.compose.material.Icon(
                                painter = painterResource(currentAdditionalIcon.iconDrawable),
                                contentDescription = currentAdditionalIcon.contentDescription,
                                Modifier.align(Alignment.Center),
                            )
                        }
                    }
                }
            } else if (basicBarConfig.additionalItems?.leftTopItem != null) {
                Spacer(Modifier.height(basicBarConfig.itemSize + basicBarConfig.spaceBetweenItems))
            }
        }
        if (basicBarConfig.basicBarPosition == BasicBarPosition.VERTICAL_LEFT)
            HoverContainerComposable(basicBarConfig, spaceBetween, hoverSelectedIndex, bottomBarItems, isHovered)
    }
}
