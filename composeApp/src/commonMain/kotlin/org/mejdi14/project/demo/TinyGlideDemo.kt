package org.mejdi14.project.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.mejdi14.project.data.tinyGlideItems
import org.mejdi14.tinyGlide.bottombar.TinyGlideBottomBar
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener

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