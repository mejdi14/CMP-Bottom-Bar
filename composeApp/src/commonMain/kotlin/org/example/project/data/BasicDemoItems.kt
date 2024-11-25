package org.example.project.data

import androidx.compose.ui.graphics.Color
import basic.example.component.data.BasicItem
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.calendar_day
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.papers
import kmp_bottom_bar.composeapp.generated.resources.the_plus_icon
import org.example.core.bottombar.data.BottomBarIcon

val basicDemoItems =
    listOf(
        BasicItem(
            BottomBarIcon(Res.drawable.home_line), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            hoverText = "Home"

        ),
        BasicItem(
            BottomBarIcon(Res.drawable.papers), "Papers",
            backgroundColor = Color(0xFFE6D9A2),
            hoverText = "read the available papers"

        ),

        BasicItem(
            BottomBarIcon(Res.drawable.calendar_day), "Menu",
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "this is a hover text"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.menu_meatballs), "Mosque",
            backgroundColor = Color(0xFFAAB396),
           hoverText = "go to the next page"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.open_reader), "Menu",
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "read a new book"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.the_plus_icon), "Mosque",
            backgroundColor = Color(0xFFAAB396),
           hoverText = "add more options"
        ),
    )

val basicDemoItems2 =
    listOf(
        BasicItem(
            BottomBarIcon(Res.drawable.home_line, iconTintColor = Color.Black), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            hoverText = "Home",



        ),
        BasicItem(
            BottomBarIcon(Res.drawable.papers, iconTintColor = Color.Black), "Papers",
            backgroundColor = Color(0xFFE6D9A2),
            hoverText = "read the available papers"

        ),

        BasicItem(
            BottomBarIcon(Res.drawable.calendar_day, iconTintColor = Color.Black), "Menu",
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "this is a hover text"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.menu_meatballs, iconTintColor = Color.Black), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            hoverText = "go to the next page"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.open_reader, iconTintColor = Color.Black), "Menu",
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "read a new book"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.the_plus_icon, iconTintColor = Color.Black), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            hoverText = "add more options"
        ),
    )

val bottomRoundItems =
    listOf(
        BasicItem(
            BottomBarIcon(Res.drawable.home_line, iconTintColor = Color.White, selectedIconTint = Color.Black), "Mosque",
            backgroundColor = Color(0xFFAAB396),
            ),
        BasicItem(
            BottomBarIcon(Res.drawable.papers, iconTintColor = Color.Black), "Papers",
            backgroundColor = Color(0xFFE6D9A2),

        ),

        BasicItem(
            BottomBarIcon(Res.drawable.calendar_day, iconTintColor = Color.Black), "Menu",
            backgroundColor = Color(0xFFFFAF00),
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.menu_meatballs, iconTintColor = Color.Black), "Mosque",
            backgroundColor = Color(0xFFAAB396),
        ),

    )