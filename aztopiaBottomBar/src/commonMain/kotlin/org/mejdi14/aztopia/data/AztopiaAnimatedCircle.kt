package org.mejdi14.aztopia.data

import androidx.compose.ui.graphics.Color
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.core.bottombar.helpers.random

data class AztopiaAnimatedCircle (
    val backgroundColor: Color = Color.random(),
    val icon: BottomBarIcon
)