package org.mejdi14.project.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.the_plus_icon
import org.mejdi14.aztopia.bottombar.AztopiaBottomBar
import org.mejdi14.aztopia.data.AztopiaAnimatedCircle
import org.mejdi14.aztopia.data.AztopiaAnimatedComposable
import org.mejdi14.aztopia.data.AztopiaItem
import org.mejdi14.aztopia.helper.AztopiaTrio
import org.mejdi14.aztopia.listeners.AztopiaActionListener
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.project.data.aztopiaItems

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
