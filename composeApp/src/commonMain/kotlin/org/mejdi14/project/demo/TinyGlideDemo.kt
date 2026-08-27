package org.mejdi14.project.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import kmp_bottom_bar.composeapp.generated.resources.calendar_day
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.papers
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.tinyGlide.bottombar.TinyGlideBottomBar
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

@Composable
fun TinyGlideDemo(modifier: Modifier = Modifier) {
    var itemSize by remember { mutableStateOf(50f) }
    var itemSpacing by remember { mutableStateOf(8f) }
    var selectedScale by remember { mutableStateOf(1.3f) }
    var itemCount by remember { mutableStateOf(4) }
    var palette by remember { mutableStateOf("Colorful") }

    val items = remember(itemSize, itemSpacing, selectedScale, itemCount, palette) {
        tinyGlideDemoItems(
            itemSize = itemSize.dp,
            itemSpacing = itemSpacing.dp,
            selectedScale = selectedScale,
            colorful = palette == "Colorful",
        ).take(itemCount)
    }

    Box(modifier.fillMaxSize()) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 92.dp),
        ) {
            PlaygroundSlider(
                label = "Item size",
                value = itemSize,
                valueRange = 40f..68f,
                onValueChange = { itemSize = it },
            )
            PlaygroundSlider(
                label = "Item spacing",
                value = itemSpacing,
                valueRange = 2f..18f,
                onValueChange = { itemSpacing = it },
            )
            PlaygroundSlider(
                label = "Selected scale",
                value = selectedScale,
                valueRange = 1.05f..1.55f,
                onValueChange = { selectedScale = it },
                valueText = "${(selectedScale * 100).toInt()}%",
            )
            PlaygroundOptions(
                label = "Items",
                options = listOf("3", "4"),
                selected = itemCount.toString(),
                onSelected = { itemCount = it.toInt() },
            )
            PlaygroundOptions(
                label = "Palette",
                options = listOf("Colorful", "Minimal"),
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
    selectedScale: Float,
    colorful: Boolean,
): List<TinyGlideItem> {
    val icons = listOf(
        BottomBarIcon(Res.drawable.home_line, contentDescription = "Home"),
        BottomBarIcon(Res.drawable.papers, contentDescription = "Library"),
        BottomBarIcon(Res.drawable.calendar_day, contentDescription = "Calendar"),
        BottomBarIcon(Res.drawable.menu_meatballs, contentDescription = "More"),
    )
    val colors = if (colorful) {
        listOf(
            Color(0xFF7C3AED),
            Color(0xFF2563EB),
            Color(0xFF059669),
            Color(0xFFEA580C),
        )
    } else {
        List(4) { Color(0xFF27272A) }
    }

    return icons.mapIndexed { index, icon ->
        tinyGlideItem(
            icon = icon,
            size = itemSize,
            spacing = itemSpacing,
            selectedScale = selectedScale,
            backgroundColor = colors[index],
            subItems = listOf(
                tinyGlideItem(
                    icon = BottomBarIcon(Res.drawable.open_reader, contentDescription = "Quick action"),
                    size = itemSize * 0.78f,
                    spacing = 3.dp,
                    selectedScale = 1.1f,
                    backgroundColor = colors[index],
                ),
                tinyGlideItem(
                    icon = BottomBarIcon(Res.drawable.papers, contentDescription = "Recent"),
                    size = itemSize * 0.78f,
                    spacing = 3.dp,
                    selectedScale = 1.1f,
                    backgroundColor = colors[index],
                ),
            ),
        )
    }
}

private fun tinyGlideItem(
    icon: BottomBarIcon,
    size: Dp,
    spacing: Dp,
    selectedScale: Float,
    backgroundColor: Color,
    subItems: List<TinyGlideItem> = emptyList(),
): TinyGlideItem = TinyGlideItem(
    icon = icon.copy(
        tint = Color.White.copy(alpha = 0.76f),
        selectedTint = Color.White,
    ),
    size = size,
    itemSeparationSpace = spacing,
    onSelectItemSizeChangeFriction = selectedScale,
    backgroundColor = backgroundColor,
    selectedBackgroundColor = backgroundColor.copy(alpha = 0.82f),
    subTinyGlideItems = subItems,
)
