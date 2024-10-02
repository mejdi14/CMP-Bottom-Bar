package org.example.project.items

import androidx.compose.ui.graphics.Color
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.icon1
import kmp_bottom_bar.composeapp.generated.resources.icon13
import kmp_bottom_bar.composeapp.generated.resources.icon2
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.BottomBarTitle
import org.example.tinyGlide.data.TinyGlideItem

val homeItem = TinyGlideItem(
    icon = BottomBarIcon(Res.drawable.icon13),
    contentDescription = "Home Screen",
    unselectedBackgroundColor = Color(0xFFCDC1FF),
    subTinyGlideItems = listOf(
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon1),
            "Read Papers",
            unselectedBackgroundColor = Color(0xFFCDC1FF)
        ),
        TinyGlideItem(
            BottomBarIcon(Res.drawable.icon2),
            "Visit Mosque",
            unselectedBackgroundColor = Color(0xFFCDC1FF)
        )
    )
)