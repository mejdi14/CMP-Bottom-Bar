package org.mejdi14.project

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import basic.mejdi14.component.bottombar.BasicBottomBar
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarPosition
import basic.mejdi14.component.data.BasicItem
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.the_plus_icon
import org.mejdi14.core.bottombar.data.BottomBarAdditionalItems
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.data.GlobalBottomBarIcon
import org.mejdi14.core.bottombar.indicator.BasicIndicatorShapeType
import org.mejdi14.core.bottombar.indicator.SelectedIndicatorConfig
import org.mejdi14.gooey.GooeyBottomBar
import org.mejdi14.project.data.basicDemoItems
import org.mejdi14.project.data.bottomRoundItems
import org.mejdi14.project.demo.AztopiaDemo
import org.mejdi14.project.demo.TinyGlideDemo
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(Modifier.fillMaxSize().background(Color.White)) {
            //TinyGlideDemo(Modifier.align(Alignment.BottomCenter))
            //AztopiaDemo(Modifier.align(Alignment.BottomCenter))
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
                    hoveredBackgroundColor = Color(0xFF6067eb),
                    selectedIndicatorConfig = SelectedIndicatorConfig(
                        shapeType = BasicIndicatorShapeType.Circle,
                        thickness = 3.dp,
                        color = Color.White
                    ),
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
                    itemSize = 45.dp,
                    shape = RoundedCornerShape(10.dp),
                    globalBasicIconConfig = GlobalBottomBarIcon(
                        iconTintColor = Color.White,
                        selectedIconTintColor = Color.Black
                    ),
                    additionalItems = BottomBarAdditionalItems(
                        rightBottomItem = BasicItem(
                            backgroundColor = Color.Red,
                            icon = BottomBarIcon(
                                Res.drawable.the_plus_icon
                            )
                        )
                    ),
                    backgroundColor = Color.Black,
                    hoveredBackgroundColor = Color(0xFFFFF59D),
                    selectedIndicatorConfig = SelectedIndicatorConfig(
                        shapeType = BasicIndicatorShapeType.Line,
                        thickness = 3.dp,
                        color = Color.White
                    ),
                ),
                parentModifier = Modifier.align(
                    Alignment.BottomCenter
                )
            ) {
            }
            /*var separated = remember { mutableStateOf(false) }
            val separationAnim = animateDpAsState(
                targetValue = if (separated.value) 30.dp else 0.dp,
                animationSpec = spring()
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GooeyBottomBar(
                    separationDp = separationAnim.value.value
                )

            }*/
        }
    }
}



