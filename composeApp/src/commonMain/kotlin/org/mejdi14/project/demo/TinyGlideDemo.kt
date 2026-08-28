package org.mejdi14.project.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.icon1
import kmp_bottom_bar.composeapp.generated.resources.icon10
import kmp_bottom_bar.composeapp.generated.resources.icon11
import kmp_bottom_bar.composeapp.generated.resources.icon12
import kmp_bottom_bar.composeapp.generated.resources.icon13
import kmp_bottom_bar.composeapp.generated.resources.icon2
import kmp_bottom_bar.composeapp.generated.resources.icon3
import kmp_bottom_bar.composeapp.generated.resources.icon4
import kmp_bottom_bar.composeapp.generated.resources.icon5
import kmp_bottom_bar.composeapp.generated.resources.icon6
import kmp_bottom_bar.composeapp.generated.resources.icon7
import kmp_bottom_bar.composeapp.generated.resources.icon8
import kmp_bottom_bar.composeapp.generated.resources.icon9
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.tinyGlide.bottombar.TinyGlideBottomBar
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun TinyGlideDemo(modifier: Modifier = Modifier) {
    var itemSize by remember { mutableStateOf(54f) }
    var itemSpacing by remember { mutableStateOf(10f) }
    var childSpacing by remember { mutableStateOf(8f) }
    var selectedScale by remember { mutableStateOf(1f) }
    var itemCount by remember { mutableStateOf(10) }
    var palette by remember { mutableStateOf("Storybook") }

    val items = remember(itemSize, itemSpacing, childSpacing, selectedScale, itemCount, palette) {
        tinyGlideDemoItems(
            itemSize = itemSize.dp,
            itemSpacing = itemSpacing.dp,
            childSpacing = childSpacing.dp,
            selectedScale = selectedScale,
            storybookPalette = palette == "Storybook",
        ).take(itemCount)
    }

    Box(modifier.fillMaxSize()) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 112.dp),
        ) {
            PlaygroundSlider(
                label = "Item size",
                value = itemSize,
                valueRange = 44f..64f,
                onValueChange = { itemSize = it },
            )
            PlaygroundSlider(
                label = "Item spacing",
                value = itemSpacing,
                valueRange = 4f..18f,
                onValueChange = { itemSpacing = it },
            )
            PlaygroundSlider(
                label = "Child spacing",
                value = childSpacing,
                valueRange = 4f..16f,
                onValueChange = { childSpacing = it },
            )
            PlaygroundSlider(
                label = "Selected scale",
                value = selectedScale,
                valueRange = 1f..1.7f,
                onValueChange = { selectedScale = it },
                valueText = "${(selectedScale * 100).toInt()}%",
            )
            PlaygroundOptions(
                label = "Items",
                options = listOf("6", "10", "13"),
                selected = itemCount.toString(),
                onSelected = { itemCount = it.toInt() },
            )
            PlaygroundOptions(
                label = "Palette",
                options = listOf("Storybook", "Minimal"),
                selected = palette,
                onSelected = { palette = it },
            )
        }

        TinyGlideBottomBar(
            bottomBarItems = items,
            parentModifier = Modifier.align(Alignment.BottomCenter),
            tinyGlideActionListener = object : TinyGlideActionListener {
                override fun onClick(item: TinyGlideItem, index: Int?) = Unit

                override fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>) = Unit
            },
        )
    }
}

private fun tinyGlideDemoItems(
    itemSize: Dp,
    itemSpacing: Dp,
    childSpacing: Dp,
    selectedScale: Float,
    storybookPalette: Boolean,
): List<TinyGlideItem> {
    val illustrations = listOf(
        Res.drawable.icon13,
        Res.drawable.icon3,
        Res.drawable.icon8,
        Res.drawable.icon10,
        Res.drawable.icon4,
        Res.drawable.icon5,
        Res.drawable.icon6,
        Res.drawable.icon7,
        Res.drawable.icon9,
        Res.drawable.icon11,
        Res.drawable.icon12,
        Res.drawable.icon1,
        Res.drawable.icon2,
    )
    val storybookColors = listOf(
        Color(0xFF756474),
        Color(0xFFED8A67),
        Color(0xFFF2A078),
        Color(0xFFF1C66C),
        Color(0xFFE5D6B8),
        Color(0xFFA8B493),
        Color(0xFFBDC47F),
        Color(0xFF91A97F),
        Color(0xFF9AB5B8),
        Color(0xFF8D9392),
        Color(0xFFB8C9CF),
        Color(0xFFEAA082),
        Color(0xFFC9B28F),
    )
    val parentColors = if (storybookPalette) {
        storybookColors
    } else {
        List(illustrations.size) { Color(0xFF27272A) }
    }

    return illustrations.mapIndexed { parentIndex, resource ->
        val childCount = if (parentIndex % 3 == 0) 3 else 2
        val children = List(childCount) { childIndex ->
            val illustrationIndex = (parentIndex + childIndex + 1) % illustrations.size
            tinyGlideItem(
                icon = illustratedIcon(
                    resource = illustrations[illustrationIndex],
                    description = "Child action ${childIndex + 1}",
                ),
                size = itemSize * 0.78f,
                spacing = childSpacing,
                selectedScale = 1.1f,
                backgroundColor = parentColors[illustrationIndex],
            )
        }
        tinyGlideItem(
            icon = illustratedIcon(resource, "Story item ${parentIndex + 1}"),
            size = itemSize,
            spacing = itemSpacing,
            selectedScale = selectedScale,
            backgroundColor = parentColors[parentIndex],
            subItems = children,
        )
    }
}

private fun illustratedIcon(
    resource: DrawableResource,
    description: String,
): BottomBarIcon = BottomBarIcon(
    resource = resource,
    tint = Color.Unspecified,
    selectedTint = Color.Unspecified,
    contentDescription = description,
    sizeReduction = 8.dp,
)

private fun tinyGlideItem(
    icon: BottomBarIcon,
    size: Dp,
    spacing: Dp,
    selectedScale: Float,
    backgroundColor: Color,
    subItems: List<TinyGlideItem> = emptyList(),
): TinyGlideItem = TinyGlideItem(
    icon = icon,
    size = size,
    shape = RoundedCornerShape(8.dp),
    itemSeparationSpace = spacing,
    parentAndSubVerticalSeparationSpace = 12.dp,
    hoverCancelDurationMillis = 220,
    onSelectItemSizeChangeFriction = selectedScale,
    backgroundColor = backgroundColor,
    selectedBackgroundColor = backgroundColor,
    subTinyGlideItems = subItems,
)
