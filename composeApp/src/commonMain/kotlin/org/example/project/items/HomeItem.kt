package org.example.project.items

import androidx.compose.ui.graphics.Color
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.icon1
import kmp_bottom_bar.composeapp.generated.resources.icon13
import kmp_bottom_bar.composeapp.generated.resources.icon2
import kmp_bottom_bar.composeapp.generated.resources.papers
import org.example.core.bottombar.BottomBarIcon
import org.example.tinyGlide.data.TinyGlideItem

val homeItem = TinyGlideItem(
    icon = BottomBarIcon(Res.drawable.icon13),
    contentDescription = "Home Screen",
    title = "Home",
    unselectedBackgroundColor = Color(0xFFCDC1FF),
    subTinyGlideItems = listOf(
        TinyGlideItem(BottomBarIcon(Res.drawable.icon1), "Read Papers", title = "Papers", unselectedBackgroundColor = Color(0xFFCDC1FF)),
        TinyGlideItem(BottomBarIcon(Res.drawable.icon2), "Visit Mosque", title = "Mosque", unselectedBackgroundColor = Color(0xFFCDC1FF))
    )
)