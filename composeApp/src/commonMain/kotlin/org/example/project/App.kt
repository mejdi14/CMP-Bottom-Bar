package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.aztopia.bottombar.AztopiaBottomBar
import org.example.aztopia.data.AztopiaAnimatedComposable
import org.example.aztopia.data.AztopiaItem
import org.example.aztopia.listeners.AztopiaActionListener
import org.example.project.data.aztopiaItems
import org.example.project.demo.AztopiaDemo
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(Modifier.fillMaxSize()) {
            //TinyGlideDemo(Modifier.align(Alignment.BottomCenter))
             AztopiaDemo(Modifier.align(Alignment.BottomCenter))
        }
    }
}


