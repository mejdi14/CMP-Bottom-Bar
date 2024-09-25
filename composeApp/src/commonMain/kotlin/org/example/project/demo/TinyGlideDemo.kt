package org.example.project.demo

import androidx.compose.runtime.Composable
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
import org.example.core.bottombar.BottomBarIcon
import org.example.project.data.bottomBarItems
import org.example.project.items.homeItem
import org.example.tinyGlide.bottombar.TinyGlideBottomBar
import org.example.tinyGlide.data.TinyGlideItem
import org.example.tinyGlide.listeners.TinyGlideActionListener

@Composable
 fun TinyGlideDemo(alignModifier: Modifier) {

    TinyGlideBottomBar(
        bottomBarItems,
        alignModifier,
        tinyGlideActionListener = object : TinyGlideActionListener {
            override fun onTinyGlideItemClickListener(item: TinyGlideItem, index: Int) {
                // nothing
            }

            override fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>) {

            }

        })
}