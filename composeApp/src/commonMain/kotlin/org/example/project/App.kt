package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.icon10
import kmp_bottom_bar.composeapp.generated.resources.icon11
import kmp_bottom_bar.composeapp.generated.resources.icon12
import kmp_bottom_bar.composeapp.generated.resources.icon3
import kmp_bottom_bar.composeapp.generated.resources.icon4
import kmp_bottom_bar.composeapp.generated.resources.icon5
import kmp_bottom_bar.composeapp.generated.resources.icon6
import kmp_bottom_bar.composeapp.generated.resources.icon7
import kmp_bottom_bar.composeapp.generated.resources.icon8
import kmp_bottom_bar.composeapp.generated.resources.icon9
import org.example.aztopia.bottombar.AztopiaBottomBar
import org.example.aztopia.data.AztopiaItem
import org.example.aztopia.listeners.AztopiaActionListener
import org.example.core.bottombar.BottomBarIcon
import org.example.project.data.aztopiaItems
import org.example.project.data.bottomBarItems
import org.example.project.demo.TinyGlideDemo
import org.example.project.items.homeItem
import org.example.tinyGlide.bottombar.TinyGlideBottomBar
import org.example.tinyGlide.data.TinyGlideItem
import org.example.tinyGlide.listeners.TinyGlideActionListener
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(Modifier.fillMaxSize()) {
            //TinyGlideDemo(Modifier.align(Alignment.BottomCenter))
            AztopiaBottomBar(
                aztopiaItems,
                Modifier.align(Alignment.BottomCenter),
                aztopiaActionListener = object : AztopiaActionListener {
                    override fun onAztopiaItemClickListener(item: AztopiaItem, index: Int) {
                        //TODO("Not yet implemented")
                    }

                    override fun onBonusItemClickListener(
                        item: AztopiaItem,
                        index: Pair<Int, Int>
                    ) {
                        //TODO("Not yet implemented")
                    }

                })
        }
    }
}


