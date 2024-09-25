package org.example.aztopia.bottombar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_bottom_bar.aztopiabottombar.generated.resources.Res
import kmp_bottom_bar.aztopiabottombar.generated.resources.close_icon
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun AztopiaAnimatedCircles(
    parentMaxWidth: Dp,
    parentMaxHeight: Dp,
    spreadOut: MutableState<Boolean>
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    val initialAngle = (PI / 2).toFloat()
    val angles = remember {
        List(4) { Animatable(initialAngle) }
    }

    val circleSize: Dp = 70.dp
    val radius: Float = with(density) { 22.dp.toPx() }

    val colors = listOf(Color.Black, Color.Green, Color.Yellow, Color.Magenta)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier

    ) {
        angles.forEachIndexed { index, animatable ->
            with(density) {
                val x = cos(animatable.value) * radius
                val y = sin(animatable.value) * radius
                val offset = Offset(x, y)
                Box(
                    modifier = Modifier.align(Alignment.TopCenter)
                        .size(circleSize)
                        .offset(
                            x = (offset.x.dp - circleSize / 2) + (parentMaxWidth / 2),
                            y = (offset.y.dp - circleSize / 2) - (parentMaxHeight / 2)
                        )
                        .background(colors[index], CircleShape)

                )
            }
        }
    }
    IconButton(
        onClick = {
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
        },
        modifier = Modifier
            .size(circleSize)
            .offset(
                x = (parentMaxWidth / 2),
                y = -(parentMaxHeight / 2)
            )
            .background(Color.Red)
    ) {
        Icon(
            painter = painterResource(Res.drawable.close_icon),
            contentDescription = "close icon"
        )
    }
}