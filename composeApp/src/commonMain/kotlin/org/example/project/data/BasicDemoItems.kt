package org.example.project.data

import androidx.compose.ui.graphics.Color
import co.touchlab.kermit.Logger
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.calendar_day
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.icon11
import kmp_bottom_bar.composeapp.generated.resources.icon12
import kmp_bottom_bar.composeapp.generated.resources.icon4
import kmp_bottom_bar.composeapp.generated.resources.icon5
import kmp_bottom_bar.composeapp.generated.resources.icon6
import kmp_bottom_bar.composeapp.generated.resources.icon7
import kmp_bottom_bar.composeapp.generated.resources.icon9
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.papers
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.listener.HoverActionListener
import org.example.tinyGlide.data.TinyGlideItem

val basicDemoItems =
    listOf(
        TinyGlideItem(
            BottomBarIcon(Res.drawable.home_line), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon9), "Papers"),
            )
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.papers), "Papers",
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
            BottomBarIcon(Res.drawable.calendar_day), "Menu",
            backgroundColor = Color(0xFFFFAF00),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon11), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon12), "Papers"),
            )
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.menu_meatballs), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon9), "Papers"),
            )
        ),
    )