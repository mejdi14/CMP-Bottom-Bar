package org.example.core.bottombar

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

 class BottomBarIcon (
      val selectedIconDrawable: DrawableResource,
      val unselectedIconDrawable: DrawableResource = selectedIconDrawable,
      val selectedIconTint: Color = Color.White,
      val unselectedIconTint: Color = Color.Black,
      val contentDescription : String = "bottom bar icon",
      val modifier : Modifier = Modifier
      )