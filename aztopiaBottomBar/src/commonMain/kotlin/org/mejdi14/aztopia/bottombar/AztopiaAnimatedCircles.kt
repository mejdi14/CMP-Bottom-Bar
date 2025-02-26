package org.mejdi14.aztopia.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.aztopia.animation.handleSpreadOutAnimation
import org.mejdi14.aztopia.data.AztopiaAnimatedComposable
import org.mejdi14.aztopia.listeners.AztopiaActionListener
import org.jetbrains.compose.resources.painterResource
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun AztopiaAnimatedCircles(
    aztopiaAnimatedComposable: AztopiaAnimatedComposable,
    parentMaxWidth: Dp,
    parentMaxHeight: Dp,
    spreadOut: MutableState<Boolean>,
    aztopiaActionListener: AztopiaActionListener
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    val initialAngle = (PI / 2).toFloat()
    val angles = remember {
        List(4) { Animatable(initialAngle) }
    }

    val baseIconRotation = animateFloatAsState(
        if (spreadOut.value) 45f else 0f
    )
    val baseIconColorAnimation = animateColorAsState(
        if (spreadOut.value) aztopiaAnimatedComposable.icon.selectedIconTint else aztopiaAnimatedComposable.icon.iconTintColor
    )
    val bonusIconsScale = animateFloatAsState(
        if (spreadOut.value) 1f else 0f
    )

    val circleSize: Dp = aztopiaAnimatedComposable.size
    val radius: Float = with(density) { (aztopiaAnimatedComposable.circularMovementRadius).toPx() }

    val colors = listOf(
        aztopiaAnimatedComposable.backgroundColor,
        aztopiaAnimatedComposable.animatedCircleItems.first.backgroundColor,
        aztopiaAnimatedComposable.animatedCircleItems.second.backgroundColor,
        aztopiaAnimatedComposable.animatedCircleItems.third.backgroundColor
    )
    val icons =
        listOf(
            null,
            aztopiaAnimatedComposable.animatedCircleItems.first.icon,
            aztopiaAnimatedComposable.animatedCircleItems.second.icon,
            aztopiaAnimatedComposable.animatedCircleItems.third.icon
        )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier

    ) {

        angles.forEachIndexed { index, animatable ->
            val x = cos(animatable.value) * radius
            val y = sin(animatable.value) * radius
            val offset = Offset(x, y)
            val additionalVerticalOffset =
                (if (index != 0 && !spreadOut.value) (((aztopiaAnimatedComposable.itemsOffsetOverlayFriction * 4) - (index * aztopiaAnimatedComposable.itemsOffsetOverlayFriction)).dp) else 0.dp)
            Box(
                modifier = Modifier.align(Alignment.TopCenter)
                    .size(if (index == 0) circleSize else circleSize - aztopiaAnimatedComposable.sizeDifference)
                    .offset(
                        x = (offset.x.dp - circleSize / 2) + (parentMaxWidth / 2),
                        y = (offset.y.dp - circleSize / 2) - (parentMaxHeight / 2) + additionalVerticalOffset
                    )
                    .background(colors[index], CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        aztopiaActionListener.onAnimatedCircularItemClickListener(index)
                    }
            ) {
                icons[index]?.let {
                    Icon(
                        painter = painterResource(it.iconDrawable),
                        contentDescription = it.contentDescription,
                        tint = it.iconTintColor,
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
            .size(aztopiaAnimatedComposable.size)
            .offset(
                x = (parentMaxWidth / 2) - (circleSize / 2),
                y = -(parentMaxHeight / 2) + 20.dp
            ),

        ) {
        Icon(
            painter = painterResource(aztopiaAnimatedComposable.icon.iconDrawable),
            tint = baseIconColorAnimation.value,
            contentDescription = aztopiaAnimatedComposable.contentDescription,
            modifier = Modifier
                .align(Alignment.Center)
                .size(circleSize / 2)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    handleSpreadOutAnimation(spreadOut, initialAngle, angles, scope)
                }
                .rotate(baseIconRotation.value)
                .then(aztopiaAnimatedComposable.icon.modifier)

        )
    }
}
