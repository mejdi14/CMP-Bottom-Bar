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
import org.example.core.bottombar.BottomBarItem
import org.example.tinyGlide.data.TinyGlideItem
import org.jetbrains.compose.resources.painterResource


@Composable
fun TinyGlideBottomBar(
    bottomBarItems: List<TinyGlideItem>,
    parentModifier: Modifier,
    onIconClick: (BottomBarItem) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState()
    var itemPositions = remember { mutableMapOf<Int, Offset>() }
    var selectedItem by remember { mutableStateOf<Int?>(null) }
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
                        selectedIndex.value = index
                        onIconClick(item)
                    },
                    modifier = Modifier.size(animatedParentWidth).align(Alignment.Center)
                        .onGloballyPositioned { layoutCoordinates ->
                            // Store the position of each item
                            itemPositions[index] = layoutCoordinates.positionInWindow()
                        }
                        .background(
                            color = Color.Black, shape = RoundedCornerShape(10.dp)
                        )
                        .hoverEffect { onHover ->
                            parentItemDynamicSize =
                                if (onHover) item.size * item.onSelectItemSizeChangeFriction else item.size
                            selectedItem = index
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
        selectedItem?.let { index ->
            itemPositions[index]?.let { position ->
                val density = LocalDensity.current.density

                LazyRow(
                    state = lazyListState,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.align(Alignment.TopStart)
                        .offset(
                            x = (position.x / density).dp - ((30.dp + (50.dp * 3) + (50.dp * 1.3f)) / 2),
                            y = (-60).dp
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
}

@Composable
private fun Dp.toPx() = this.value * LocalDensity.current.density