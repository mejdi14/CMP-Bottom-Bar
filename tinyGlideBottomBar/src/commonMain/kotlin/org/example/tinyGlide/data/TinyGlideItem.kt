package org.example.tinyGlide.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.BottomBarIdentifier
import org.example.core.bottombar.BottomBarItem
import org.jetbrains.compose.resources.DrawableResource

data class TinyGlideItem(
    override val identifier: BottomBarIdentifier,
    override val size: Dp = 50.dp,
    override val icon: DrawableResource,
    override val title: String,
    override val contentDescription: String,
    val radius: Dp,
    val backgroundColor: Color
) : BottomBarItem()