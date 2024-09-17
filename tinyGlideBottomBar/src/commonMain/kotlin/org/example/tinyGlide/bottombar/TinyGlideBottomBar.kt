package org.example.tinyGlide.bottombar

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.core.bottombar.BottomBarItem
import org.example.tinyGlide.data.TinyGlideItem
import org.jetbrains.compose.resources.painterResource


@Composable
fun TinyGlideBottomBar(
    bottomBarItems: List<TinyGlideItem>,
    parentModifier: Modifier,
    onIconClick: (BottomBarItem) -> Unit
) {
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    val lazyListState = rememberLazyListState()
    var selectedItem = remember { mutableStateOf<TinyGlideItem?>(null) }
    val scope = rememberCoroutineScope()
    val hoverExitJob = remember { mutableStateOf<Job?>(null) }
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
                Box(
                    Modifier.width(item.itemSeparationSpace)
                )
                IconButton(
                    onClick = {
                        selectedIndex.value = if (selectedIndex.value == null) index else null
                        onIconClick(item)
                        selectedItem.value = if (selectedIndex.value == null) item else null
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
                            color = item.backgroundColor, shape = item.itemShape
                        )
                        .hoverEffect { onHover ->
                            isHovering.value = onHover
                            if (onHover) {
                                hoverExitJob.value?.cancel()
                                hoverExitJob.value = null

                                item.parentItemDynamicSize.value = item.size * item.onSelectItemSizeChangeFriction
                                selectedItem.value = item
                            } else {
                                hoverExitJob.value = scope.launch {
                                    delay(500) // Delay for 0.5 seconds
                                    selectedItem.value = null
                                    item.parentItemDynamicSize.value = item.size
                                }
                            }
                        }
                ) {
                    Icon(
                        painter = painterResource(item.icon.selectedIconDrawable),
                        contentDescription = item.icon.contentDescription,
                        tint = Color.White,
                        modifier = item.icon.modifier.align(Alignment.Center)
                    )
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
            onIconClick
        )
    }
}

