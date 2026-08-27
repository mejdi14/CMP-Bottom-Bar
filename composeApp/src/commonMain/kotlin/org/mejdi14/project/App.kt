package org.mejdi14.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basic.mejdi14.component.bottombar.BasicBottomBar
import basic.mejdi14.component.data.BasicBarAdditionalItems
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarIconStyle
import basic.mejdi14.component.data.BasicBarPosition
import basic.mejdi14.component.data.BasicItem
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.the_plus_icon
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorConfig
import org.mejdi14.core.bottombar.indicator.BottomBarIndicatorShape
import org.mejdi14.project.data.basicDemoItems
import org.mejdi14.project.data.bottomRoundItems

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(Modifier.fillMaxSize().background(AppBackground)) {
            BasicBottomBar(
                items = lightDemoItems,
                config = BasicBarConfig(
                    position = BasicBarPosition.HorizontalTop,
                    containerColor = LightSurface,
                    hoverColor = LightHover,
                    iconStyle = LightIconStyle,
                    indicator = BottomBarIndicatorConfig(
                        shapeType = BottomBarIndicatorShape.DOT,
                        color = DarkAccent,
                    ),
                ),
                modifier = Modifier.align(Alignment.TopCenter),
            ) { _, _ -> }
            BasicBottomBar(
                items = darkDemoItems,
                config = BasicBarConfig(
                    position = BasicBarPosition.VerticalLeft,
                    containerColor = DarkSurface,
                    hoverColor = DarkHover,
                    iconStyle = DarkIconStyle,
                    indicator = BottomBarIndicatorConfig(color = DarkSelection),
                ),
                modifier = Modifier.align(Alignment.CenterStart),
            ) { _, _ -> }
            BasicBottomBar(
                items = lightDemoItems,
                config = BasicBarConfig(
                    position = BasicBarPosition.VerticalRight,
                    containerColor = LightSurface,
                    hoverColor = LightHover,
                    iconStyle = LightIconStyle,
                    indicator = BottomBarIndicatorConfig(
                        shapeType = BottomBarIndicatorShape.LINE,
                        thickness = 3.dp,
                        color = DarkAccent,
                    ),
                ),
                modifier = Modifier.align(Alignment.CenterEnd),
            ) { _, _ -> }
            BasicBottomBar(
                items = darkBottomItems,
                config = BasicBarConfig(
                    position = BasicBarPosition.HorizontalBottom,
                    itemSize = 45.dp,
                    shape = RoundedCornerShape(10.dp),
                    iconStyle = DarkIconStyle,
                    additionalItems = BasicBarAdditionalItems(
                        endItem = BasicItem(
                            backgroundColor = DarkItemSurface,
                            icon = BottomBarIcon(
                                resource = Res.drawable.the_plus_icon,
                                contentDescription = "Add",
                            ),
                        ),
                    ),
                    containerColor = DarkSurface,
                    hoverColor = DarkHover,
                    indicator = BottomBarIndicatorConfig(
                        shapeType = BottomBarIndicatorShape.LINE,
                        thickness = 3.dp,
                        color = LightAccent,
                    ),
                ),
                modifier = Modifier.align(Alignment.BottomCenter),
            ) { _, _ -> }
        }
    }
}

private val AppBackground = Color(0xFFF4F4F5)
private val LightSurface = Color(0xFFFFFFFF)
private val LightItemSurface = Color(0xFFF4F4F5)
private val LightHover = Color(0xFFE4E4E7)
private val LightSelection = Color(0xFFD4D4D8)
private val DarkSurface = Color(0xFF18181B)
private val DarkItemSurface = Color(0xFF27272A)
private val DarkHover = Color(0xFF3F3F46)
private val DarkSelection = Color(0xFF52525B)
private val DarkAccent = Color(0xFF18181B)
private val LightAccent = Color(0xFFFAFAFA)

private val LightIconStyle = BasicBarIconStyle(
    tint = Color(0xFF52525B),
    selectedTint = Color(0xFF18181B),
)

private val DarkIconStyle = BasicBarIconStyle(
    tint = Color(0xFFA1A1AA),
    selectedTint = Color(0xFFFAFAFA),
)

private val lightDemoItems = basicDemoItems.withItemColors(
    backgroundColor = LightItemSurface,
    selectedBackgroundColor = LightSelection,
)

private val darkDemoItems = basicDemoItems.withItemColors(
    backgroundColor = DarkItemSurface,
    selectedBackgroundColor = DarkSelection,
)

private val darkBottomItems = bottomRoundItems.withItemColors(
    backgroundColor = DarkItemSurface,
    selectedBackgroundColor = DarkSelection,
)

private fun List<BasicItem>.withItemColors(
    backgroundColor: Color,
    selectedBackgroundColor: Color,
): List<BasicItem> = map { item ->
    item.copy(
        backgroundColor = backgroundColor,
        selectedBackgroundColor = selectedBackgroundColor,
    )
}
