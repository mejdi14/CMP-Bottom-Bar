package org.mejdi14.project.data

import androidx.compose.ui.unit.dp
import basic.mejdi14.component.data.BasicItem
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.expand_book
import kmp_bottom_bar.composeapp.generated.resources.expand_calendar
import kmp_bottom_bar.composeapp.generated.resources.expand_grid
import kmp_bottom_bar.composeapp.generated.resources.expand_home
import kmp_bottom_bar.composeapp.generated.resources.expand_layers
import kmp_bottom_bar.composeapp.generated.resources.expand_plus
import org.mejdi14.core.bottombar.data.BottomBarIcon

val expandableDemoItems = listOf(
    BasicItem(
        icon = BottomBarIcon(
            resource = Res.drawable.expand_home,
            contentDescription = "Home",
            sizeReduction = 18.dp,
        ),
    ),
    BasicItem(
        icon = BottomBarIcon(
            resource = Res.drawable.expand_layers,
            contentDescription = "Layers",
            sizeReduction = 18.dp,
        ),
    ),
    BasicItem(
        icon = BottomBarIcon(
            resource = Res.drawable.expand_calendar,
            contentDescription = "Calendar",
            sizeReduction = 18.dp,
        ),
    ),
    BasicItem(
        icon = BottomBarIcon(
            resource = Res.drawable.expand_grid,
            contentDescription = "Apps",
            sizeReduction = 18.dp,
        ),
    ),
    BasicItem(
        icon = BottomBarIcon(
            resource = Res.drawable.expand_book,
            contentDescription = "Library",
            sizeReduction = 18.dp,
        ),
    ),
    BasicItem(
        icon = BottomBarIcon(
            resource = Res.drawable.expand_plus,
            contentDescription = "Add",
            sizeReduction = 18.dp,
        ),
    ),
)
