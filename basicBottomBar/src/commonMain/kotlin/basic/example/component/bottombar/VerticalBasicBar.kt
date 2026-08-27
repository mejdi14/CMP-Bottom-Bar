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
    onIconClick: (BasicItem) -> Unit,
) {
    Row(parentModifier) {
        if (basicBarConfig.basicBarPosition == BasicBarPosition.VERTICAL_RIGHT)
            HoverContainerComposable(
                basicBarConfig,
                spaceBetween,
                hoverSelectedIndex,
                bottomBarItems,
                isHovered
            )
        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (basicBarConfig.additionalItems?.startItem != null) {
                Column {
                    Box(
                        Modifier.size(basicBarConfig.itemSize + basicBarConfig.aroundItemsPadding)
                            .clickable {
                                basicBarConfig.additionalItems.startItem
                                    .takeIf { it.interaction.enabled }
                                    ?.let { it.onClick.onClick(it, null) }
                            }
                            .background(
                                basicBarConfig.additionalItems.startItem.backgroundColor,
                                shape = basicBarConfig.additionalItems.startItem.shape,
                            )
                    ) {
                        val currentAdditionalIcon =
                            basicBarConfig.additionalItems.startItem.icon
                        androidx.compose.material.Icon(
                            painter = painterResource(currentAdditionalIcon.resource),
                            contentDescription = currentAdditionalIcon.contentDescription,
                            Modifier.align(Alignment.Center).size(
                                basicBarConfig.additionalItems.startItem.size
                            ).padding(currentAdditionalIcon.sizeReduction),
                        )
                    }
                    Spacer(Modifier.height(basicBarConfig.spaceBetweenItems))
                }
            } else if (basicBarConfig.additionalItems?.endItem != null) {
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
                                    item.onHover.onHover(item, onHover)
                                }
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    if (item.interaction.shouldDispatchClick(selectedIndex.value, index)) {
                                        selectedIndex.value = item.interaction
                                            .nextSelectedIndex(selectedIndex.value, index) ?: selectedIndex.value
                                        item.onClick.onClick(item, index)
                                        onIconClick(item)
                                    }
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
                                basicBarConfig.iconStyle,
                                item,
                                Modifier.align(Alignment.Center).size(item.size),
                                selectedIndex.value == index
                            )
                        }
                    }
                }
            }
            if (basicBarConfig.additionalItems?.endItem != null) {
                Column {
                    Spacer(Modifier.height(basicBarConfig.spaceBetweenItems))
                    Box(
                        Modifier.size(basicBarConfig.itemSize + basicBarConfig.aroundItemsPadding)
                            .clickable {
                                basicBarConfig.additionalItems.endItem
                                    .takeIf { it.interaction.enabled }
                                    ?.let { it.onClick.onClick(it, null) }
                            }
                            .background(
                                basicBarConfig.additionalItems.endItem.backgroundColor,
                                shape = basicBarConfig.additionalItems.endItem.shape,
                            )
                    ) {
                        val currentAdditionalIcon =
                            basicBarConfig.additionalItems.endItem.icon
                        androidx.compose.material.Icon(
                            painter = painterResource(currentAdditionalIcon.resource),
                            contentDescription = currentAdditionalIcon.contentDescription,
                            Modifier.align(Alignment.Center).size(
                                basicBarConfig.additionalItems.endItem.size
                            ).padding(currentAdditionalIcon.sizeReduction),
                        )
                    }
                }
            } else if (basicBarConfig.additionalItems?.startItem != null) {
                Spacer(Modifier.height(basicBarConfig.itemSize + basicBarConfig.spaceBetweenItems))
            }
        }
        if (basicBarConfig.basicBarPosition == BasicBarPosition.VERTICAL_LEFT)
            HoverContainerComposable(
                basicBarConfig,
                spaceBetween,
                hoverSelectedIndex,
                bottomBarItems,
                isHovered
            )
    }
}
