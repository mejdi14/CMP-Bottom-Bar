package org.mejdi14.project.data

import basic.mejdi14.component.data.BasicItem
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.calendar_day
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
            hoverText = "Home",
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.papers, contentDescription = "Papers"),
            hoverText = "Papers",
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.calendar_day, contentDescription = "Calendar"),
            hoverText = "Calendar",
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.menu_meatballs, contentDescription = "Menu"),
            hoverText = "Menu",
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.open_reader, contentDescription = "Reader"),
            hoverText = "Reader",
        ),
        BasicItem(
            BottomBarIcon(Res.drawable.the_plus_icon, contentDescription = "Add"),
            hoverText = "Add",
        ),
    )
