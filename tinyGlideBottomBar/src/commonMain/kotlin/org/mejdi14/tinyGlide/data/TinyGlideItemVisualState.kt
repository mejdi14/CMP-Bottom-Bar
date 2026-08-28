package org.mejdi14.tinyGlide.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
data class TinyGlideItemVisualState(
    val isSelected: Boolean,
    val isExpanded: Boolean,
    val isHovered: Boolean,
    val isFocused: Boolean,
    val displaySize: Dp,
)
