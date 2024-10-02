package org.example.project.data

import androidx.compose.ui.graphics.Color
import co.touchlab.kermit.Logger
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.home_line
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
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import org.example.aztopia.data.AztopiaItem
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.listener.HoverActionListener
import org.example.project.items.homeItem
import org.example.tinyGlide.data.TinyGlideItem

val tinyGlideItems =
    listOf(
        homeItem,
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon3), "Papers",
            backgroundColor = Color(0xFFE6D9A2),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon4), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon5), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon6), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon7), "Papers"),
            ),
            hoverActionListener = object : HoverActionListener<TinyGlideItem> {
                override fun onHoverEnter(item: TinyGlideItem) {
                    Logger.i("hover enter")
                }

                override fun onHoverExit(item: TinyGlideItem) {
                    Logger.i("hover exit")
                }

                override fun onHoverParentItem(item: TinyGlideItem) {
                    Logger.i("hover parent enter")
                }

                override fun onHoverSubItem(item: TinyGlideItem) {
                    Logger.i("hover sub enter")
                }

            }
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon8), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon9), "Papers"),
            )
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon10), "Menu",
            backgroundColor = Color(0xFFFFAF00),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon11), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon12), "Papers"),
            )
        ),
    )

val aztopiaItems = listOf(
    AztopiaItem(
        BottomBarIcon(Res.drawable.home_line, iconTintColor = Color.Black),
        "Papers",
        backgroundColor = Color(0xFFE6D9A2),
    ),
    AztopiaItem(
        BottomBarIcon(Res.drawable.menu_meatballs, iconTintColor = Color.Black),
        "Papers",
        backgroundColor = Color(0xFFE6D9A2),
    ),
)
