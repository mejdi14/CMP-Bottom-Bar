package org.example.core.bottombar

import org.jetbrains.compose.resources.DrawableResource

data class BottomBarItem(
    val identifier: BottomBarIdentifier,
    val icon: DrawableResource,
    val title: String,
    val contentDescription: String = ""
)

