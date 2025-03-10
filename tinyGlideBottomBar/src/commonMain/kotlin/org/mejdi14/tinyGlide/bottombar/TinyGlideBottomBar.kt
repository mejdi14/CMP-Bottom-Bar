package org.mejdi14.tinyGlide.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.isSelectedItem
import org.mejdi14.tinyGlide.helper.handleHoverAction
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener


@Composable
fun TinyGlideBottomBar(
    bottomBarItems: List<TinyGlideItem>,
    parentModifier: Modifier,
    tinyGlideActionListener: TinyGlideActionListener
) {
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    val lazyListState = rememberLazyListState()
    val selectedItem = remember { mutableStateOf<TinyGlideItem?>(null) }
    val hoverExitJob = remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val isHovering = remember { mutableStateOf(false) }
    Box(
        parentModifier.fillMaxWidth().padding(5.dp)
    ) {
        LazyRow(
            state = lazyListState,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(bottomBarItems) { index, item ->
                val animatedParentWidth by animateDpAsState(
                    targetValue = item.parentItemDynamicSize.value,
                    animationSpec = tween(durationMillis = item.onSelectItemSizeChangeDurationMillis)
                )
                item.index = index
                Box(
                    Modifier.width(item.itemSeparationSpace)
                )
                IconButton(
                    onClick = {
                        item.clickActionListener.onItemClickListener()
                        tinyGlideActionListener.onItemClickListener(item, index)
                        selectedIndex.value = index
                        selectedItem.value = if (selectedItem.value == item) null else item
                    },
                    modifier = Modifier.size(animatedParentWidth).align(Alignment.Center)
                        .onGloballyPositioned { layoutCoordinates ->
                            if (item.itemCoordinatesOffset == null || (kotlin.math.abs(
                                    (item.itemCoordinatesOffset?.x
                                        ?: 0f) - layoutCoordinates.positionInWindow().x
                                ) > item.marginForScreenSizeChanges)
                            )
                                item.itemCoordinatesOffset = layoutCoordinates.positionInWindow()
                        }
                        .background(
                            color = if (item.isSelectedItem(selectedItem.value))
                                item.selectedBackgroundColor
                            else item.backgroundColor,
                            shape = item.itemShape
                        )
                        .hoverEffect { onHover ->
                            handleHoverAction(
                                isHovering,
                                onHover,
                                item,
                                selectedItem,
                                hoverExitJob,
                                scope
                            )
                        }
                ) {
                    TinyGlideIcon(item, selectedItem, Modifier.align(Alignment.Center))
                }
                Box(Modifier.width(item.itemSeparationSpace))
            }
        }
        SubItemsComposable(
            Modifier.align(Alignment.TopStart),
            selectedItem,
            selectedIndex,
            lazyListState,
            hoverExitJob,
            isHovering,
            scope,
            tinyGlideActionListener
        )
    }
}



