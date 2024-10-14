package org.example.project.data

import androidx.compose.ui.graphics.Color
import basic.example.component.data.BasicItem
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
import org.example.core.bottombar.data.BottomBarIcon
import org.example.core.bottombar.listener.HoverActionListener
import org.example.tinyGlide.data.TinyGlideItem

val basicDemoItems =
    listOf(
        BasicItem(
            BottomBarIcon(Res.drawable.home_line), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            hoverText = "salam"

        ),
        BasicItem(
            BottomBarIcon(Res.drawable.papers), "Papers",
            backgroundColor = Color(0xFFE6D9A2),
            hoverText = "ti winak ya 3ami 3mor"

        ),

        BasicItem(
            BottomBarIcon(Res.drawable.calendar_day), "Menu",
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "this is a hover text"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.menu_meatballs), "Mosque",
            backgroundColor = Color(0xFFAAB396),
           hoverText = "a simple one"
        ),
    )