package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import basic.example.component.bottombar.BasicBottomBar
import basic.example.component.data.BasicBarConfig
import basic.example.component.data.BasicBarPosition
import org.example.project.data.basicDemoItems
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
                BasicBarConfig(basicBarPosition = BasicBarPosition.VERTICAL_LEFT),
                parentModifier = Modifier.align(
                    Alignment.BottomCenter
                )
            ) {

            }
        }
    }
}


