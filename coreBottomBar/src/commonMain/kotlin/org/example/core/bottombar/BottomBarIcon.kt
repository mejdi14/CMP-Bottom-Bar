package org.example.core.bottombar

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

class BottomBarIcon (
      val selectedIconDrawable: DrawableResource,
      val unselectedIconDrawable: DrawableResource = selectedIconDrawable,
      val selectedIconTint: Color = Color.Unspecified,
      val unselectedIconTint: Color = Color.Unspecified,
      val contentDescription : String = "bottom bar icon",
      val modifier : Modifier = Modifier,
      val sizeDifferenceComparedToParent : Dp = 5.dp
      )