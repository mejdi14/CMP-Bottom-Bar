package org.mejdi14.tinyGlide.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import org.mejdi14.tinyGlide.data.TinyGlideAnimationConfig
import org.mejdi14.tinyGlide.enum.AnimationType

@Composable
fun getEnterTransition(animation: TinyGlideAnimationConfig): EnterTransition {
    return when (animation.childAppearanceAnimation) {
        AnimationType.FADE -> fadeIn(
            animationSpec = tween(
                durationMillis = animation.childAppearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        )

        AnimationType.SLIDE -> slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(
                durationMillis = animation.childAppearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = animation.childAppearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        )

        AnimationType.SCALE -> scaleIn(
            initialScale = 0f,
            animationSpec = tween(
                durationMillis = animation.childAppearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        )
    }
}

@Composable
fun getExitTransition(animation: TinyGlideAnimationConfig): ExitTransition {
    return when (animation.childAppearanceAnimation) {
        AnimationType.FADE -> fadeOut(
            animationSpec = tween(
                durationMillis = animation.childDisappearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        )

        AnimationType.SLIDE -> slideOutVertically(
            targetOffsetY = { it / 2 },
            animationSpec = tween(
                durationMillis = animation.childDisappearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = animation.childDisappearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        )

        AnimationType.SCALE -> scaleOut(
            targetScale = 0f,
            animationSpec = tween(
                durationMillis = animation.childDisappearanceDurationMillis,
                easing = animation.childAppearanceEasing,
            ),
        )
    }
}
