package org.mejdi14.project.data

import androidx.compose.ui.graphics.Color
import basic.mejdi14.component.data.BasicItem
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.calendar_day
import kmp_bottom_bar.composeapp.generated.resources.home_black
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.papers
import kmp_bottom_bar.composeapp.generated.resources.the_plus_icon
import org.mejdi14.core.bottombar.data.BottomBarIcon

val basicDemoItems =
    listOf(
        BasicItem(
            BottomBarIcon(Res.drawable.home_line, contentDescription = "Home"),
            backgroundColor = Color(0xFFAAB396),
            hoverText = "Home"

        ),
        BasicItem(
            BottomBarIcon(Res.drawable.papers, contentDescription = "Papers"),
            backgroundColor = Color(0xFFE6D9A2),
            hoverText = "read the available papers"

        ),

        BasicItem(
            BottomBarIcon(Res.drawable.calendar_day, contentDescription = "Calendar"),
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "this is a hover text"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.menu_meatballs, contentDescription = "Menu"),
            backgroundColor = Color(0xFFAAB396),
           hoverText = "go to the next page"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.open_reader, contentDescription = "Reader"),
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "read a new book"
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.the_plus_icon, contentDescription = "Add"),
            backgroundColor = Color(0xFFAAB396),
           hoverText = "add more options"
        ),
    )

val basicDemoItems2 =
    listOf(
        BasicItem(
            BottomBarIcon(
                Res.drawable.home_line,
                tint = Color.Black,
                contentDescription = "Home",
            ),
            backgroundColor = Color(0xFFAAB396),
            hoverText = "Home",



        ),
        BasicItem(
            BottomBarIcon(
                Res.drawable.papers,
                tint = Color.Black,
                contentDescription = "Papers",
            ),
            backgroundColor = Color(0xFFE6D9A2),
            hoverText = "read the available papers"

        ),

        BasicItem(
            BottomBarIcon(
                Res.drawable.calendar_day,
                tint = Color.Black,
                contentDescription = "Calendar",
            ),
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "this is a hover text"
        ),
        BasicItem(
            BottomBarIcon(
                Res.drawable.menu_meatballs,
                tint = Color.Black,
                contentDescription = "Menu",
            ),
            backgroundColor = Color(0xFFAAB396),
            hoverText = "go to the next page"
        ),
        BasicItem(
            BottomBarIcon(
                Res.drawable.open_reader,
                tint = Color.Black,
                contentDescription = "Reader",
            ),
            backgroundColor = Color(0xFFFFAF00),
            hoverText = "read a new book"
        ),
        BasicItem(
            BottomBarIcon(
                Res.drawable.the_plus_icon,
                tint = Color.Black,
                contentDescription = "Add",
            ),
            backgroundColor = Color(0xFFAAB396),
            hoverText = "add more options"
        ),
    )

val bottomRoundItems =
    listOf(
        BasicItem(
            BottomBarIcon(
                Res.drawable.home_black,
                tint = Color.White,
                selectedTint = Color.Black,
                contentDescription = "Home",
            ),
            backgroundColor = Color(0xFFAAB396),
            ),
        BasicItem(
            BottomBarIcon(
                Res.drawable.papers,
                tint = Color.Black,
                contentDescription = "Papers",
            ),
            backgroundColor = Color(0xFFE6D9A2),

        ),
        BasicItem(
            BottomBarIcon(
                Res.drawable.menu_meatballs,
                tint = Color.Black,
                contentDescription = "Menu",
            ),
            backgroundColor = Color(0xFFAAB396),
        ),
    )
