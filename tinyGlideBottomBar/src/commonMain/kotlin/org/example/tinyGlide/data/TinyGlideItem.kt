package org.example.tinyGlide.data

import androidx.compose.ui.unit.Dp
import org.example.core.bottombar.BottomBarIdentifier
import org.example.core.bottombar.BottomBarItem
import org.jetbrains.compose.resources.DrawableResource

data class TinyGlideItem(
    val radius: Dp,
    override val size: Dp,
    override val identifier: BottomBarIdentifier,
    override val icon: DrawableResource,
    override val title: String,
    override val contentDescription: String
) : BottomBarItem()