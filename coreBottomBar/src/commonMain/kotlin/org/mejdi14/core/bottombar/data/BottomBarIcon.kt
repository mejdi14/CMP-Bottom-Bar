package org.mejdi14.core.bottombar.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

data class BottomBarIcon(
    val resource: DrawableResource,
    val selectedResource: DrawableResource = resource,
    val tint: Color = Color.Unspecified,
    val selectedTint: Color = tint,
    val contentDescription: String? = null,
    val modifier: Modifier = Modifier,
    val sizeReduction: Dp = 10.dp,
)
