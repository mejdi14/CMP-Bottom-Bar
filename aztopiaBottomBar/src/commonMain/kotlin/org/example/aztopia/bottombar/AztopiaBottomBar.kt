package org.example.aztopia.bottombar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.math.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.example.aztopia.data.AztopiaItem
import org.example.aztopia.listeners.AztopiaActionListener


@Composable
fun AztopiaBottomBar(
    bottomBarItems: List<AztopiaItem>,
    parentModifier: Modifier,
    aztopiaActionListener: AztopiaActionListener
) {
    var spreadOut = remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    val lazyListState = rememberLazyListState()
    val selectedItem = remember { mutableStateOf<AztopiaItem?>(null) }
    val hoverExitJob = remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val isHovering = remember { mutableStateOf(false) }
    Box(modifier = parentModifier.fillMaxWidth().height(100.dp).background(Color.Red)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = parentModifier.fillMaxWidth().height(70.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f).background(Color.Blue).fillMaxHeight()
            ) {
                bottomBarItems.filterIndexed { index, _ -> index % 2 == 0 }
                    .forEach { item ->
                        AztopiaIcon(item, selectedItem)
                    }
            }

            Spacer(Modifier.width(60.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                bottomBarItems.filterIndexed { index, _ -> index % 2 != 0 }
                    .forEach { item ->
                        AztopiaIcon(item, selectedItem)
                    }
            }
        }
        val density = LocalDensity.current
        val scope = rememberCoroutineScope()

        val initialAngle = (PI / 2).toFloat()  // 90 degrees, 6 o'clock position
        // Make sure Animatable values are not re-created on each recomposition
        val angles = remember {
            List(4) { Animatable(initialAngle) }
        }

        val circleSize: Dp = 60.dp
        val radius: Float = with(density) { 60.dp.toPx() }

        val colors = listOf(Color.Black, Color.Green, Color.Yellow, Color.Magenta)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clickable {

                        // Flip the spreadOut state immediately to ensure the animation triggers on first click
                        spreadOut.value = !spreadOut.value
                        val targetAngles = if (spreadOut.value) {
                            listOf(
                                initialAngle,  // Black stays at 6 o'clock
                                PI.toFloat(),  // Green moves to 9 o'clock
                                (3 * PI / 2).toFloat(),  // Yellow moves to 12 o'clock
                                (2 * PI).toFloat()  // Magenta moves to 3 o'clock
                            )
                        } else {
                            List(4) { initialAngle }  // All return to 6 o'clock
                        }
                        angles.forEachIndexed { index, animatable ->
                            scope.launch {
                            animatable.animateTo(
                                targetAngles[index],
                                animationSpec = TweenSpec(durationMillis = 1000)
                            )
                        }
                    }
                }
        ) {
            angles.forEachIndexed { index, animatable ->
                with(density) {
                    val x = cos(animatable.value) * radius
                    val y = sin(animatable.value) * radius
                    val offset = Offset(x, y)
                    Box(
                        modifier = Modifier
                            .size(circleSize)
                            .offset(
                                x = (offset.x.dp - circleSize / 2) + 100.dp,
                                y = (offset.y.dp - circleSize / 2) -  100.dp
                            )
                            .background(colors[index], CircleShape)
                    )
                }
            }
        }
    }
}