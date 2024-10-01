package org.example.aztopia.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.PI

internal fun handleSpreadOutAnimation(
    spreadOut: MutableState<Boolean>,
    initialAngle: Float,
    angles: List<Animatable<Float, AnimationVector1D>>,
    scope: CoroutineScope
) {
    spreadOut.value = !spreadOut.value
    val targetAngles = if (spreadOut.value) {
        listOf(
            initialAngle,
            PI.toFloat(),
            (3 * PI / 2).toFloat(),
            (2 * PI).toFloat()
        )
    } else {
        List(4) { initialAngle }
    }
    angles.forEachIndexed { index, animatable ->
        scope.launch {
            animatable.animateTo(
                targetAngles[index],
                animationSpec = SpringSpec(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow,

                    )
            )
        }
    }
}
