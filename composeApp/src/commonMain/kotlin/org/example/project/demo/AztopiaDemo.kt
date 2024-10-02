package org.example.project.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.the_plus_icon
import org.example.aztopia.bottombar.AztopiaBottomBar
import org.example.aztopia.data.AztopiaAnimatedCircle
import org.example.aztopia.data.AztopiaAnimatedComposable
import org.example.aztopia.data.AztopiaItem
import org.example.aztopia.helper.AztopiaTrio
import org.example.aztopia.listeners.AztopiaActionListener
import org.example.core.bottombar.BottomBarIcon
import org.example.project.data.aztopiaItems

@Composable
fun AztopiaDemo(alignModifier: Modifier) {
    AztopiaBottomBar(
        aztopiaItems,
        AztopiaAnimatedComposable(
          BottomBarIcon(iconDrawable = Res.drawable.the_plus_icon),
            animatedCircleItems = AztopiaTrio(
                AztopiaAnimatedCircle(icon = BottomBarIcon(iconDrawable = Res.drawable.open_reader)),
                AztopiaAnimatedCircle(icon = BottomBarIcon(iconDrawable = Res.drawable.open_reader)),
                AztopiaAnimatedCircle(icon = BottomBarIcon(iconDrawable = Res.drawable.open_reader)),
            )
        ),
                alignModifier,
        aztopiaActionListener = object : AztopiaActionListener {
            override fun onItemClickListener(item: AztopiaItem, index: Int) {
                //TODO("Not yet implemented")
            }

            override fun onAnimatedCircularItemClickListener(
                index: Int
            ) {
                //TODO("Not yet implemented")
            }

        })
}