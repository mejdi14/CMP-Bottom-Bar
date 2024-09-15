package org.example.tinyGlide.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
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
    var selectedItem by remember { mutableStateOf<TinyGlideItem?>(null) }
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
                var parentItemDynamicSize by remember { mutableStateOf(item.size) }
                val animatedParentWidth by animateDpAsState(
                    targetValue = parentItemDynamicSize,
                    animationSpec = tween(durationMillis = item.onSelectItemSizeChangeDurationMillis)
                )
                Box(
                    Modifier.width(item.itemSeparationSpace)
                )
                IconButton(
                    onClick = {
                        selectedIndex.value = if (selectedIndex.value == null) index else null
                        onIconClick(item)
                        selectedItem = if (selectedIndex.value == null) item else null
                    },
                    modifier = Modifier.size(animatedParentWidth).align(Alignment.Center)
                        .onGloballyPositioned { layoutCoordinates ->
                            // if (item.itemCoordinatesOffset == null)
                            item.itemCoordinatesOffset = layoutCoordinates.positionInWindow()
                        }
                        .background(
                            color = Color.Black, shape = RoundedCornerShape(10.dp)
                        )
                        .hoverEffect { onHover ->
                            parentItemDynamicSize =
                                if (onHover) item.size * item.onSelectItemSizeChangeFriction else item.size
                            selectedItem = if (onHover) item else null
                        }

                ) {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.contentDescription,
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(Modifier.width(item.itemSeparationSpace))
            }
        }
        selectedItem?.let { currentItem ->
            val density = LocalDensity.current.density
            Logger.i("item $selectedIndex = ${currentItem.itemCoordinatesOffset}")
            LazyRow(
                state = lazyListState,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.align(Alignment.TopStart)
                    .offset(
                        x = ((currentItem.itemCoordinatesOffset?.x
                            ?: 0f) / density).dp - (((currentItem.itemSeparationSpace * (bottomBarItems.size - 1)) + (currentItem.size * (bottomBarItems.size - 1)) + (currentItem.size * currentItem.onSelectItemSizeChangeFriction)) / 2),
                        y = -(currentItem.size + currentItem.parentAndSubVerticalSeparationSpace)
                    )
            ) {
                itemsIndexed(bottomBarItems) { index, item ->
                    val parentItemDynamicSize by remember { mutableStateOf(item.size) }
                    val animatedParentWidth by animateDpAsState(
                        targetValue = parentItemDynamicSize,
                        animationSpec = tween(durationMillis = item.onSelectItemSizeChangeDurationMillis)
                    )
                    Box(
                        Modifier.width(item.itemSeparationSpace)
                    )
                    IconButton(
                        onClick = {
                            selectedIndex.value = index
                            onIconClick(item)
                        },
                        modifier = Modifier.size(animatedParentWidth).align(Alignment.Center)

                            .background(
                                color = Color.Black, shape = RoundedCornerShape(10.dp)
                            )


                    ) {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.contentDescription,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Box(Modifier.width(item.itemSeparationSpace))
                }
            }
        }
    }
}

