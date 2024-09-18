package org.example.core.bottombar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.DrawableResource

abstract class BottomBarItem{
    abstract val title: String
    abstract val size : Dp
    abstract val contentDescription: String
    abstract val backgroundColor: Color
    abstract val itemShape: Shape
    abstract val icon: BottomBarIcon
    abstract val index: Int
}

