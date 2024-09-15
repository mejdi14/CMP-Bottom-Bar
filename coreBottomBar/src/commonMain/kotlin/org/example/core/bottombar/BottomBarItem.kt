package org.example.core.bottombar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.DrawableResource

abstract class BottomBarItem{
    abstract val identifier: BottomBarIdentifier
    abstract val icon: DrawableResource
    abstract val title: String
    abstract val size : Dp
    abstract val contentDescription: String
    abstract val backgroundColor: Color
    abstract val itemShape: Shape
}

