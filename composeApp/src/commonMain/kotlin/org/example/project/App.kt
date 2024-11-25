package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import basic.example.component.bottombar.BasicBottomBar
import basic.example.component.data.BasicBarConfig
import basic.example.component.data.BasicBarPosition
import org.example.core.bottombar.data.BottomBarIcon
import org.example.core.bottombar.data.GlobalBottomBarIcon
import org.example.core.bottombar.indicator.SelectedIndicatorConfig
import org.example.core.bottombar.indicator.BasicIndicatorShapeType
import org.example.project.data.basicDemoItems
import org.example.project.data.basicDemoItems2
import org.example.project.data.bottomRoundItems
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(Modifier.fillMaxSize()) {
            //TinyGlideDemo(Modifier.align(Alignment.BottomCenter))
            // AztopiaDemo(Modifier.align(Alignment.BottomCenter))
            BasicBottomBar(
                bottomBarItems = basicDemoItems,
                BasicBarConfig(
                    basicBarPosition = BasicBarPosition.HORIZONTAL_TOP,
                    selectedIndicatorConfig = SelectedIndicatorConfig(shapeType = BasicIndicatorShapeType.Dot)
                ),
                parentModifier = Modifier.align(
                    Alignment.TopCenter
                )
            ) {

            }
            BasicBottomBar(
                bottomBarItems = basicDemoItems,
                BasicBarConfig(basicBarPosition = BasicBarPosition.VERTICAL_LEFT),
                parentModifier = Modifier.align(
                    Alignment.CenterStart
                )
            ) {

            }
            BasicBottomBar(
                bottomBarItems = basicDemoItems,
                BasicBarConfig(
                    basicBarPosition = BasicBarPosition.VERTICAL_RIGHT,
                    backgroundColor = Color(0xFF3841e6),
                    hoveredBackgroundColor = Color(0xFF6067eb)
                ),
                parentModifier = Modifier.align(
                    Alignment.CenterEnd
                )
            ) {

            }
            BasicBottomBar(
                bottomBarItems = bottomRoundItems,
                BasicBarConfig(
                    basicBarPosition = BasicBarPosition.HORIZONTAL_BOTTOM,
                    globalBasicIconConfig = GlobalBottomBarIcon(
                        iconTintColor = Color.White,
                        selectedIconTint = Color.Black
                    ),
                    backgroundColor = Color.Black,
                    hoveredBackgroundColor = Color(0xFFFFF59D),
                    selectedIndicatorConfig = SelectedIndicatorConfig(
                        shapeType = BasicIndicatorShapeType.Circle,
                        thickness = 3.dp,
                        color = Color.White
                    ),
                ),
                parentModifier = Modifier.align(
                    Alignment.BottomCenter
                )
            ) {

            }
        }
    }
}


