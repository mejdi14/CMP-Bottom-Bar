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
import org.mejdi14.tinyGlide.enum.AnimationType

@Composable
fun getEnterTransition(animationType: AnimationType): EnterTransition {
    return when (animationType) {
        AnimationType.FADE -> fadeIn(
            animationSpec = tween(durationMillis = 500)
        )

        AnimationType.SLIDE -> slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 500)
        )

        AnimationType.SCALE -> scaleIn(
            initialScale = 0.2f,
            animationSpec = tween(durationMillis = 300)
        )
    }
}

@Composable
fun getExitTransition(animationType: AnimationType): ExitTransition {
    return when (animationType) {
        AnimationType.FADE -> fadeOut(
            animationSpec = tween(durationMillis = 500)
        )

        AnimationType.SLIDE -> slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 500)
        )

        AnimationType.SCALE -> scaleOut(
            targetScale = 0.4f,
            animationSpec = tween(durationMillis = 0)
        )
    }
}
