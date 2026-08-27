package org.mejdi14.project.data

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
import org.mejdi14.aztopia.data.AztopiaItem
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.listener.BottomBarHoverListener
import org.mejdi14.project.items.homeItem
import org.mejdi14.tinyGlide.data.TinyGlideItem

val tinyGlideItems =
    listOf(
        homeItem,
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon3, contentDescription = "Papers"),
            backgroundColor = Color(0xFFE6D9A2),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.home_line, contentDescription = "Home")),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon5, contentDescription = "Papers")),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon6, contentDescription = "Papers")),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon7, contentDescription = "Papers")),
            ),
            onHover = BottomBarHoverListener { _, isHovered ->
                Logger.i(if (isHovered) "hover enter" else "hover exit")
            },
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon8, contentDescription = "Mosque"),
            backgroundColor = Color(0xFFAAB396),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon9, contentDescription = "Papers")),
            )
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon10, contentDescription = "Menu"),
            backgroundColor = Color(0xFFFFAF00),
            subTinyGlideItems = listOf(
                TinyGlideItem(BottomBarIcon(Res.drawable.icon11, contentDescription = "Papers")),
                TinyGlideItem(BottomBarIcon(Res.drawable.icon12, contentDescription = "Papers")),
            )
        ),
    )

val aztopiaItems = listOf(
    AztopiaItem(
        BottomBarIcon(
            Res.drawable.home_line,
            tint = Color.Black,
            contentDescription = "Papers",
        ),
        backgroundColor = Color(0xFFE6D9A2),
    ),
    AztopiaItem(
        BottomBarIcon(
            Res.drawable.menu_meatballs,
            tint = Color.Black,
            contentDescription = "Papers",
        ),
        backgroundColor = Color(0xFFE6D9A2),
    ),
)
