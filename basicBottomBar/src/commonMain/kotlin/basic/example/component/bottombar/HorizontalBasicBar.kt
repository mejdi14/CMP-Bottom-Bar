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
import basic.example.component.bottombar.addtional.LeftAdditionalItem
import basic.mejdi14.component.bottombar.hover.HoverDescriptionTextComposable
import basic.mejdi14.component.bottombar.icon.BasicBarIconComposable
import basic.mejdi14.component.bottombar.indicator.bottomBarIndicatorComposable
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarPosition
import basic.mejdi14.component.data.BasicItem
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.jetbrains.compose.resources.painterResource
import org.mejdi14.core.bottombar.data.BottomBarAdditionalItems

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
    Column(parentModifier.fillMaxWidth().padding(horizontal = basicBarConfig.aroundItemsPadding)) {
        if (basicBarConfig.basicBarPosition == BasicBarPosition.HORIZONTAL_BOTTOM)
            TopHoverComposable(basicBarConfig, spaceBetween, hoverSelectedIndex, bottomBarItems, isHovered)
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeftAdditionalItem(
                basicBarConfig,
                basicBarConfig.additionalItems?.leftTopItem as BasicItem?,
            )

            Box(
                Modifier.padding(basicBarConfig.basicBarPadding)
                    .height(basicBarConfig.itemSize + (basicBarConfig.basicBarPadding * 2))
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
            extracted(
                basicBarConfig.additionalItems,
                basicBarConfig,
                basicBarConfig.additionalItems?.rightBottomItem as BasicItem?,
            )
        }
        if (basicBarConfig.basicBarPosition == BasicBarPosition.HORIZONTAL_TOP)
            BottomHoverComposable(spaceBetween, hoverSelectedIndex, bottomBarItems, isHovered, basicBarConfig)
    }
}




@Composable
private fun extracted(
    additionalItems: BottomBarAdditionalItems?,
    basicBarConfig: BasicBarConfig,
    rightBottomItem: BasicItem?,
) {
    if (additionalItems?.rightBottomItem != null) {
        Row {
            Spacer(Modifier.width(basicBarConfig.spaceBetweenItems))
            Box(
                Modifier.size(basicBarConfig.itemSize)
                    .clickable {
                        rightBottomItem?.clickActionListener?.onItemClickListener()
                    }
                    .background(
                        color = rightBottomItem?.backgroundColor ?: basicBarConfig.backgroundColor,
                        shape = rightBottomItem?.itemShape ?: basicBarConfig.shape
                    )
            ) {
                if (rightBottomItem?.icon != null) {


                    val currentAdditionalIcon =
                        rightBottomItem.icon

                    Icon(
                        painter = painterResource(currentAdditionalIcon.iconDrawable),
                        contentDescription = currentAdditionalIcon.contentDescription,
                        Modifier.align(Alignment.Center),
                        tint = currentAdditionalIcon.iconTintColor,
                    )
                }
            }
        }
    } else if (basicBarConfig.additionalItems?.leftTopItem != null) {
        Spacer(Modifier.width(basicBarConfig.itemSize + basicBarConfig.spaceBetweenItems))
    }
}




