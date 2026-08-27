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
import androidx.compose.ui.unit.dp
import basic.mejdi14.component.bottombar.BasicBottomBar
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarIconStyle
import basic.mejdi14.component.data.BasicBarPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape
import org.mejdi14.project.data.basicDemoItems

@Composable
fun BasicDemo(modifier: Modifier = Modifier) {
    var itemSize by remember { mutableStateOf(52f) }
    var itemSpacing by remember { mutableStateOf(8f) }
    var indicatorShape by remember { mutableStateOf(BottomBarIndicatorShape.LINE) }
    var indicatorPosition by remember { mutableStateOf(BottomBarIndicatorPosition.END) }
    var barPosition by remember { mutableStateOf(BasicBarPosition.HorizontalBottom) }

    val items = remember {
        basicDemoItems.take(5).map { item ->
            item.copy(
                backgroundColor = Color(0xFF27272A),
                selectedBackgroundColor = Color(0xFF52525B),
            )
        }
    }
    val barAlignment = when (barPosition) {
        BasicBarPosition.HorizontalBottom -> Alignment.BottomCenter
        BasicBarPosition.HorizontalTop -> Alignment.TopCenter
        BasicBarPosition.VerticalLeft -> Alignment.CenterStart
        BasicBarPosition.VerticalRight -> Alignment.CenterEnd
    }

    Box(modifier.fillMaxSize()) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 76.dp),
        ) {
            PlaygroundSlider(
                label = "Item size",
                value = itemSize,
                valueRange = 42f..68f,
                onValueChange = { itemSize = it },
            )
            PlaygroundSlider(
                label = "Item spacing",
                value = itemSpacing,
                valueRange = 2f..18f,
                onValueChange = { itemSpacing = it },
            )
            PlaygroundOptions(
                label = "Indicator",
                options = listOf("Line", "Dot", "Square", "Circle"),
                selected = indicatorShape.label,
                onSelected = { indicatorShape = it.toIndicatorShape() },
            )
            PlaygroundOptions(
                label = "Indicator side",
                options = listOf("Start", "End"),
                selected = indicatorPosition.label,
                onSelected = {
                    indicatorPosition = if (it == "Start") {
                        BottomBarIndicatorPosition.START
                    } else {
                        BottomBarIndicatorPosition.END
                    }
                },
            )
            PlaygroundOptions(
                label = "Bar position",
                options = listOf("Bottom", "Left", "Right"),
                selected = barPosition.label,
                onSelected = { barPosition = it.toBarPosition() },
            )
        }

        BasicBottomBar(
            items = items,
            modifier = Modifier.align(barAlignment),
            config = BasicBarConfig(
                itemSize = itemSize.dp,
                itemSpacing = itemSpacing.dp,
                position = barPosition,
                shape = RoundedCornerShape(14.dp),
                containerColor = Color(0xFF18181B),
                hoverColor = Color(0xFF3F3F46),
                iconStyle = BasicBarIconStyle(
                    tint = Color(0xFFA1A1AA),
                    selectedTint = Color.White,
                ),
                indicator = BottomBarIndicatorConfig(
                    shapeType = indicatorShape,
                    position = indicatorPosition,
                    thickness = 4.dp,
                    color = Color.White,
                ),
            ),
        ) { _, _ -> }
    }
}

private val BottomBarIndicatorShape.label: String
    get() = name.lowercase().replaceFirstChar { it.uppercase() }

private fun String.toIndicatorShape(): BottomBarIndicatorShape = when (this) {
    "Dot" -> BottomBarIndicatorShape.DOT
    "Square" -> BottomBarIndicatorShape.SQUARE
    "Circle" -> BottomBarIndicatorShape.CIRCLE
    else -> BottomBarIndicatorShape.LINE
}

private val BottomBarIndicatorPosition.label: String
    get() = if (this == BottomBarIndicatorPosition.START) "Start" else "End"

private val BasicBarPosition.label: String
    get() = when (this) {
        BasicBarPosition.VerticalLeft -> "Left"
        BasicBarPosition.VerticalRight -> "Right"
        else -> "Bottom"
    }

private fun String.toBarPosition(): BasicBarPosition = when (this) {
    "Left" -> BasicBarPosition.VerticalLeft
    "Right" -> BasicBarPosition.VerticalRight
    else -> BasicBarPosition.HorizontalBottom
}
