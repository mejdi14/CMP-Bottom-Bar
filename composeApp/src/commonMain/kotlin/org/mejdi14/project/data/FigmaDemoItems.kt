package org.mejdi14.project.data

import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.figma_code
import kmp_bottom_bar.composeapp.generated.resources.figma_comment
import kmp_bottom_bar.composeapp.generated.resources.figma_cursor
import kmp_bottom_bar.composeapp.generated.resources.figma_design
import kmp_bottom_bar.composeapp.generated.resources.figma_draw
import kmp_bottom_bar.composeapp.generated.resources.figma_frame
import kmp_bottom_bar.composeapp.generated.resources.figma_pen
import kmp_bottom_bar.composeapp.generated.resources.figma_rectangle
import kmp_bottom_bar.composeapp.generated.resources.figma_resources
import kmp_bottom_bar.composeapp.generated.resources.figma_text
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.data.BottomBarItemGroup
import org.mejdi14.figma.bottombar.FigmaBarItem

val figmaDemoGroups = listOf(
    BottomBarItemGroup(
        key = "tools",
        items = listOf(
            figmaItem(Res.drawable.figma_cursor, "Move", hasMenu = true),
            figmaItem(Res.drawable.figma_frame, "Frame", hasMenu = true),
            figmaItem(Res.drawable.figma_rectangle, "Shape", hasMenu = true),
            figmaItem(Res.drawable.figma_pen, "Pen", hasMenu = true),
            figmaItem(Res.drawable.figma_text, "Text"),
            figmaItem(Res.drawable.figma_comment, "Comment", hasMenu = true),
            figmaItem(Res.drawable.figma_resources, "Resources"),
        ),
    ),
    BottomBarItemGroup(
        key = "modes",
        items = listOf(
            figmaItem(Res.drawable.figma_draw, "Draw"),
            figmaItem(Res.drawable.figma_design, "Design"),
            figmaItem(Res.drawable.figma_code, "Dev mode"),
        ),
    ),
)

private fun figmaItem(
    resource: org.jetbrains.compose.resources.DrawableResource,
    description: String,
    hasMenu: Boolean = false,
) = FigmaBarItem(
    icon = BottomBarIcon(
        resource = resource,
        contentDescription = description,
    ),
    hasMenu = hasMenu,
)
