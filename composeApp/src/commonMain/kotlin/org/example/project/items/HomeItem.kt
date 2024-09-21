package org.example.project.items

import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.papers
import org.example.core.bottombar.BottomBarIcon
import org.example.tinyGlide.data.TinyGlideItem

val homeItem = TinyGlideItem(
    icon = BottomBarIcon(Res.drawable.home_line),
    contentDescription = "Home Screen",
    title = "Home",
    subTinyGlideItems = listOf(
        TinyGlideItem(BottomBarIcon(Res.drawable.papers), "Read Papers", title = "Papers"),
        TinyGlideItem(BottomBarIcon(Res.drawable.home_line), "Visit Mosque", title = "Mosque")
    )
)