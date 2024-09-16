package org.example.core.bottombar

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

 class BottomBarIcon (
      val selectedIconDrawable: DrawableResource,
      val unselectedIconDrawable: DrawableResource? = null,
      val selectedIconTint: Color? = null,
      val unselectedIconTint: Color? = null,
      val contentDescription : String = "bottom bar icon",
      val modifier : Modifier = Modifier
      )