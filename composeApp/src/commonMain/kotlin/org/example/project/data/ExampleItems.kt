package org.example.project.data

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
import org.example.aztopia.data.AztopiaItem
import org.example.core.bottombar.BottomBarIcon
import org.example.project.items.homeItem
import org.example.tinyGlide.data.TinyGlideItem

val bottomBarItems =
    listOf(
        homeItem,
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon3), "Papers",
            unselectedBackgroundColor = Color(0xFFE6D9A2),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon4), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon5), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon6), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon7), "Papers"),
            )
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon8), "Mosque",
            unselectedBackgroundColor = Color(0xFFAAB396),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon9), "Papers"),
            )
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon10), "Menu",
            unselectedBackgroundColor = Color(0xFFFFAF00),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon11), "Papers"),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon12), "Papers"),
            )
        ),
    )

val aztopiaItems = listOf(
    AztopiaItem(
        BottomBarIcon(Res.drawable.icon3), "Papers",
        unselectedBackgroundColor = Color(0xFFE6D9A2),),
    AztopiaItem(
        BottomBarIcon(Res.drawable.icon3), "Papers",
        unselectedBackgroundColor = Color(0xFFE6D9A2),),
    AztopiaItem(
        BottomBarIcon(Res.drawable.icon3), "Papers",
        unselectedBackgroundColor = Color(0xFFE6D9A2),),
    AztopiaItem(
        BottomBarIcon(Res.drawable.icon3), "Papers",
        unselectedBackgroundColor = Color(0xFFE6D9A2),),
)