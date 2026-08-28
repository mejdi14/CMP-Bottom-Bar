package org.mejdi14.tinyGlide.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class TinyGlideItemDecoration(
    val label: String? = null,
    val badge: String? = null,
    val tooltip: String? = null,
    val contentColor: Color = Color.White,
    val badgeContainerColor: Color = Color(0xFFE5484D),
    val badgeContentColor: Color = Color.White,
    val tooltipContainerColor: Color = Color(0xE629292B),
    val tooltipContentColor: Color = Color.White,
)
