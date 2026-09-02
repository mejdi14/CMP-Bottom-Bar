package org.mejdi14.project.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.mejdi14.figma.bottombar.FigmaBarConfig
import org.mejdi14.figma.bottombar.FigmaBarState
import org.mejdi14.figma.bottombar.FigmaBottomBar
import org.mejdi14.project.data.figmaDemoGroups

@Composable
fun FigmaDemo(modifier: Modifier = Modifier) {
    var palette by remember { mutableStateOf("Light") }
    var itemSize by remember { mutableStateOf(38f) }
    var iconSize by remember { mutableStateOf(20f) }
    var groupSpacing by remember { mutableStateOf(8f) }
    var lastAction by remember { mutableStateOf("Move selected") }
    val state = remember { FigmaBarState(mapOf(0 to 0, 1 to 1)) }
    val baseConfig = if (palette == "Light") FigmaBarConfig.light() else FigmaBarConfig()
    val demoBackground = if (palette == "Light") Color(0xFFD8D8D8) else Color(0xFF202020)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(demoBackground),
    ) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 82.dp),
        ) {
            PlaygroundOptions(
                label = "Palette",
                options = listOf("Light", "Dark"),
                selected = palette,
                onSelected = { palette = it },
            )
            PlaygroundSlider(
                label = "Item size",
                value = itemSize,
                valueRange = 34f..46f,
                onValueChange = {
                    itemSize = it
                    iconSize = iconSize.coerceAtMost(it)
                },
            )
            PlaygroundSlider(
                label = "Icon size",
                value = iconSize,
                valueRange = 17f..24f,
                onValueChange = { iconSize = it.coerceAtMost(itemSize) },
            )
            PlaygroundSlider(
                label = "Group spacing",
                value = groupSpacing,
                valueRange = 4f..14f,
                onValueChange = { groupSpacing = it },
            )
            Text(
                text = "Last action: $lastAction",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF52525B),
            )
            Text(
                text = "Inspired by Figma’s toolbar. Figma is a trademark of Figma, Inc. This project is not affiliated with Figma.",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF71717A),
            )
        }

        FigmaBottomBar(
            groups = figmaDemoGroups,
            state = state,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            config = baseConfig.copy(
                itemSize = itemSize.dp,
                iconSize = iconSize.dp,
                groupSpacing = groupSpacing.dp,
            ),
            onItemClick = { item, _ ->
                lastAction = "${item.icon.contentDescription.orEmpty()} selected"
            },
            onItemHover = { item, _, hovered ->
                if (hovered) {
                    lastAction = "Hovering ${item.icon.contentDescription.orEmpty()}"
                }
            },
        )
    }
}
