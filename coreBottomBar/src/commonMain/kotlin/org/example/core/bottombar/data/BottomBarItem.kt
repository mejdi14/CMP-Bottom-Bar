package org.example.core.bottombar.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

abstract class BottomBarItem{
    abstract val title: BottomBarTitle
    abstract val size : Dp
    abstract val contentDescription: String
    abstract val backgroundColor: Color
    abstract val selectedBackgroundColor: Color
    abstract val itemShape: Shape
    abstract val icon: BottomBarIcon
    abstract val index: Int
    abstract val disableClickIfAlreadySelected : Boolean
    abstract val withTitleShown : Boolean
}

