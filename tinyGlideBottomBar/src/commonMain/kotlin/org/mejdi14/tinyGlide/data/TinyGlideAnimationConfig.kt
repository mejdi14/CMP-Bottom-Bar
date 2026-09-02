package org.mejdi14.tinyGlide.data

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.runtime.Immutable
import org.mejdi14.tinyGlide.enum.AnimationType

@Immutable
data class TinyGlideAnimationConfig(
    val parentHoverScale: Float = 1.3f,
    val parentSelectedScale: Float = 1.2f,
    val childHoverScale: Float = 1.3f,
    val parentHoverDurationMillis: Int = 300,
    val parentSelectionDurationMillis: Int = 300,
    val childHoverDurationMillis: Int = 300,
    val childAppearanceDurationMillis: Int = 300,
    val childDisappearanceDurationMillis: Int = 300,
    val parentHoverEasing: Easing = FastOutSlowInEasing,
    val parentSelectionEasing: Easing = FastOutSlowInEasing,
    val childHoverEasing: Easing = FastOutSlowInEasing,
    val childAppearanceEasing: Easing = FastOutSlowInEasing,
    val childAppearanceAnimation: AnimationType = AnimationType.SCALE,
)
