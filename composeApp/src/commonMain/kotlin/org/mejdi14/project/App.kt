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
        Box(Modifier.fillMaxSize().background(Color.White)) {
            BasicBottomBar(
                items = basicDemoItems,
                config = BasicBarConfig(
                    position = BasicBarPosition.HorizontalTop,
                    indicator = BottomBarIndicatorConfig(
                        shapeType = BottomBarIndicatorShape.DOT,
                    ),
                ),
                modifier = Modifier.align(
                    Alignment.TopCenter
                )
            ) { _, _ -> }
            BasicBottomBar(
                items = basicDemoItems,
                config = BasicBarConfig(position = BasicBarPosition.VerticalLeft),
                modifier = Modifier.align(
                    Alignment.CenterStart
                )
            ) { _, _ -> }
            BasicBottomBar(
                items = basicDemoItems,
                config = BasicBarConfig(
                    position = BasicBarPosition.VerticalRight,
                    containerColor = Color(0xFF3841E6),
                    hoverColor = Color(0xFF6067EB),
                    indicator = BottomBarIndicatorConfig(
                        shapeType = BottomBarIndicatorShape.LINE,
                        thickness = 3.dp,
                        color = Color.Red
                    ),
                ),
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )

            ) { _, _ -> }
            BasicBottomBar(
                items = bottomRoundItems,
                config = BasicBarConfig(
                    position = BasicBarPosition.HorizontalBottom,
                    itemSize = 45.dp,
                    shape = RoundedCornerShape(10.dp),
                    iconStyle = BasicBarIconStyle(
                        tint = Color.White,
                        selectedTint = Color.Black,
                    ),
                    additionalItems = BasicBarAdditionalItems(
                        endItem = BasicItem(
                            backgroundColor = Color.Red,
                            icon = BottomBarIcon(
                                Res.drawable.the_plus_icon
                            )
                        )
                    ),
                    containerColor = Color.Black,
                    hoverColor = Color(0xFFFFF59D),
                    indicator = BottomBarIndicatorConfig(
                        shapeType = BottomBarIndicatorShape.LINE,
                        thickness = 3.dp,
                        color = Color.White
                    ),
                ),
                modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            ) { _, _ -> }
        }
    }
}
