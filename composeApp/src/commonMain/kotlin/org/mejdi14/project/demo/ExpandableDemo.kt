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
import expand.mejdi14.expandable.bottombar.ExpandableBarConfig
import expand.mejdi14.expandable.bottombar.ExpandableBottomBar
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorPosition
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape
import org.mejdi14.project.data.basicDemoItems

@Composable
fun ExpandableDemo(modifier: Modifier = Modifier) {
    var barWidth by remember { mutableStateOf(300f) }
    var rowHeight by remember { mutableStateOf(56f) }
    var itemSize by remember { mutableStateOf(46f) }
    var rowCount by remember { mutableStateOf(2) }
    var indicatorShape by remember { mutableStateOf(BottomBarIndicatorShape.LINE) }
    var indicatorPosition by remember { mutableStateOf(BottomBarIndicatorPosition.END) }

    Box(modifier.fillMaxSize()) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 104.dp),
        ) {
            PlaygroundSlider(
                label = "Bar width",
                value = barWidth,
                valueRange = 240f..420f,
                onValueChange = { barWidth = it },
            )
            PlaygroundSlider(
                label = "Row height",
                value = rowHeight,
                valueRange = 44f..72f,
                onValueChange = { rowHeight = it },
            )
            PlaygroundSlider(
                label = "Item size",
                value = itemSize,
                valueRange = 36f..54f,
                onValueChange = { itemSize = it },
            )
            PlaygroundOptions(
                label = "Rows",
                options = listOf("1", "2", "3"),
                selected = rowCount.toString(),
                onSelected = { rowCount = it.toInt() },
            )
            PlaygroundOptions(
                label = "Indicator",
                options = listOf("Line", "Dot", "Square", "Circle"),
                selected = indicatorShape.expandableLabel,
                onSelected = { indicatorShape = it.toExpandableIndicatorShape() },
            )
            PlaygroundOptions(
                label = "Indicator side",
                options = listOf("Top", "Bottom"),
                selected = if (indicatorPosition == BottomBarIndicatorPosition.START) "Top" else "Bottom",
                onSelected = {
                    indicatorPosition = if (it == "Top") {
                        BottomBarIndicatorPosition.START
                    } else {
                        BottomBarIndicatorPosition.END
                    }
                },
            )
        }

        ExpandableBottomBar(
            bottomBarItems = basicDemoItems.take(4),
            parentModifier = Modifier.align(Alignment.BottomCenter),
            onIconClick = {},
            config = ExpandableBarConfig(
                width = barWidth.dp,
                rowHeight = rowHeight.dp,
                itemSize = itemSize.dp,
                rowCount = rowCount,
                containerColor = Color(0xFF18181B),
                indicator = BottomBarIndicatorConfig(
                    color = Color.White,
                    shapeType = indicatorShape,
                    position = indicatorPosition,
                    thickness = 4.dp,
                ),
            ),
        )
    }
}

private val BottomBarIndicatorShape.expandableLabel: String
    get() = name.lowercase().replaceFirstChar { it.uppercase() }

private fun String.toExpandableIndicatorShape(): BottomBarIndicatorShape = when (this) {
    "Dot" -> BottomBarIndicatorShape.DOT
    "Square" -> BottomBarIndicatorShape.SQUARE
    "Circle" -> BottomBarIndicatorShape.CIRCLE
    else -> BottomBarIndicatorShape.LINE
}
