package org.example.project.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.data.tinyGlideItems
import org.example.tinyGlide.bottombar.TinyGlideBottomBar
import org.example.tinyGlide.data.TinyGlideItem
import org.example.tinyGlide.listeners.TinyGlideActionListener

@Composable
 fun TinyGlideDemo(alignModifier: Modifier) {

    TinyGlideBottomBar(
        tinyGlideItems,
        alignModifier,
        tinyGlideActionListener = object : TinyGlideActionListener {
            override fun onItemClickListener(item: TinyGlideItem, index: Int) {
                // nothing
            }

            override fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>) {

            }

        })
}