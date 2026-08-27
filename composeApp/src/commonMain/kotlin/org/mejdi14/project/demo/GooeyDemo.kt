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
import androidx.compose.ui.unit.dp
import org.mejdi14.gooey.GooeyBottomBar

@Composable
fun GooeyDemo(modifier: Modifier = Modifier) {
    var itemWidth by remember { mutableStateOf(92f) }
    var itemHeight by remember { mutableStateOf(50f) }
    var separation by remember { mutableStateOf(18f) }
    var cornerRadius by remember { mutableStateOf(16f) }
    var accent by remember { mutableStateOf("Blue") }

    val accentColor = when (accent) {
        "Purple" -> Color(0xFF7C3AED)
        "Green" -> Color(0xFF059669)
        else -> Color(0xFF1279FF)
    }

    Box(modifier.fillMaxSize()) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 92.dp),
        ) {
            PlaygroundSlider(
                label = "Item width",
                value = itemWidth,
                valueRange = 68f..112f,
                onValueChange = { itemWidth = it },
            )
            PlaygroundSlider(
                label = "Item height",
                value = itemHeight,
                valueRange = 40f..66f,
                onValueChange = { itemHeight = it },
            )
            PlaygroundSlider(
                label = "Gooey separation",
                value = separation,
                valueRange = 0f..30f,
                onValueChange = { separation = it },
            )
            PlaygroundSlider(
                label = "Corner radius",
                value = cornerRadius,
                valueRange = 8f..26f,
                onValueChange = { cornerRadius = it },
            )
            PlaygroundOptions(
                label = "Accent",
                options = listOf("Blue", "Purple", "Green"),
                selected = accent,
                onSelected = { accent = it },
            )
        }

        GooeyBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            separationDp = separation,
            itemWidth = itemWidth.dp,
            itemHeight = itemHeight.dp,
            cornerRadius = cornerRadius.dp,
            selectedColor = accentColor,
            unselectedColor = Color(0xFF18181B),
        )
    }
}
