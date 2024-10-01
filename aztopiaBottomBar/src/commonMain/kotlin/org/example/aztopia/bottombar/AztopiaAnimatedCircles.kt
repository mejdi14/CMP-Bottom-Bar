package org.example.aztopia.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_bottom_bar.aztopiabottombar.generated.resources.Res
import kmp_bottom_bar.aztopiabottombar.generated.resources.calendar_day
import kmp_bottom_bar.aztopiabottombar.generated.resources.close_icon
import kmp_bottom_bar.aztopiabottombar.generated.resources.home_line
import kmp_bottom_bar.aztopiabottombar.generated.resources.open_reader
import kmp_bottom_bar.aztopiabottombar.generated.resources.papers
import kmp_bottom_bar.aztopiabottombar.generated.resources.the_plus_icon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.example.aztopia.animation.handleSpreadOutAnimation
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

    val plusIconRotation = animateFloatAsState(
        if (spreadOut.value) 45f else 0f
    )
    val plusIconColorAnimation = animateColorAsState(
        if (spreadOut.value) Color.Black else Color.White
    )
    val bonusIconsScale = animateFloatAsState(
        if (spreadOut.value) 1f else 0f
    )

    val circleSize: Dp = 70.dp
    val radius: Float = with(density) { 22.dp.toPx() }

    val colors = listOf(Color(0xFFFFFFFF), Color(0xFFEA686C), Color(0xFFC66CAD), Color(0xFF631beb))
    val icons = listOf(null, Res.drawable.calendar_day, Res.drawable.papers, Res.drawable.open_reader)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier

    ) {

        angles.forEachIndexed { index, animatable ->
            val x = cos(animatable.value) * radius
            val y = sin(animatable.value) * radius
            val offset = Offset(x, y)
            val additionalVerticalOffset =
                (if (index != 0 && !spreadOut.value) ((20 - (index * 5)).dp) else 0.dp)
            Box(
                modifier = Modifier.align(Alignment.TopCenter)
                    .size(if (index == 0) circleSize + 10.dp else circleSize)
                    .offset(
                        x = (offset.x.dp - circleSize / 2) + (parentMaxWidth / 2),
                        y = (offset.y.dp - circleSize / 2) - (parentMaxHeight / 2) + additionalVerticalOffset
                    )
                    .background(colors[index], CircleShape)

            ) {
                icons[index]?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = "close icon",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(circleSize / 3)
                            .scale(bonusIconsScale.value)


                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .size(circleSize + 10.dp)
            .offset(
                x = (parentMaxWidth / 2) - (circleSize / 2),
                y = -(parentMaxHeight / 2) + 20.dp
            ),

        ) {
        Icon(
            painter = painterResource(Res.drawable.the_plus_icon),
            tint = plusIconColorAnimation.value,
            contentDescription = "close icon",
            modifier = Modifier
                .align(Alignment.Center)
                .size(circleSize / 2)
                .clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = null){
                    handleSpreadOutAnimation(spreadOut, initialAngle, angles, scope)
                }
                .rotate(plusIconRotation.value)

        )
    }
}
