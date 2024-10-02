package org.example.aztopia.data

import androidx.compose.ui.graphics.Color
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.helpers.random

data class AztopiaAnimatedCircle (
    val backgroundColor: Color = Color.random(),
    val icon: BottomBarIcon
)