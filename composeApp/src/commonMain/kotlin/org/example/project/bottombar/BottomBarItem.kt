package ui

import muslimunited.composeapp.generated.resources.Res
import muslimunited.composeapp.generated.resources.home_line
import muslimunited.composeapp.generated.resources.menu_meatballs
import muslimunited.composeapp.generated.resources.open_reader
import muslimunited.composeapp.generated.resources.papers
import org.jetbrains.compose.resources.DrawableResource

data class BottomBarItem(
    val identifier: BottomBarIdentifier,
    val icon: DrawableResource,
    val title: String,
    val contentDescription: String = ""
)

val bottomBarItems =
    listOf(
        BottomBarItem(BottomBarIdentifier.Home, Res.drawable.home_line, "Home"),
        BottomBarItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
        BottomBarItem(BottomBarIdentifier.Mosque, Res.drawable.open_reader, "Mosque"),
        BottomBarItem(BottomBarIdentifier.Menu, Res.drawable.menu_meatballs, "Menu"),
    )