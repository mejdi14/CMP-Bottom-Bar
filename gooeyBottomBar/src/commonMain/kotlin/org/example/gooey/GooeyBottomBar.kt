package org.example.gooey

import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun GooeyBottomBar(
    modifier: Modifier = Modifier,
    separationDp: Float
) {
    val color = Color(0xFF1279ff)
    val selectedIndex = remember { mutableStateOf(0) }
    Box(){
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .platformBlur(blurRadius = 55f, alphaMultiplier = 50f, alphaOffset = -5000f)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                val separationAnim = animateDpAsState(
                    targetValue = if (selectedIndex.value == 0) 20.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(if (selectedIndex.value == 0) color else Color.Black)

                )
                Spacer(Modifier.width(separationAnim.value))
            }

            Row {
                val separationAnim = animateDpAsState(
                    targetValue = if (selectedIndex.value == 1) 20.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(if (selectedIndex.value == 1) color else Color.Black)
                )
                Spacer(Modifier.width(separationAnim.value))
            }
            Row {
                val separationAnim = animateDpAsState(
                    targetValue = if (selectedIndex.value == 2) 20.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(if (selectedIndex.value == 2) color else Color.Black)
                )
                Spacer(Modifier.width(separationAnim.value))
            }
        }
        Row(
                modifier = modifier
                    .padding(10.dp),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                val separationAnim = animateDpAsState(
                    targetValue = if (selectedIndex.value == 0) 20.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(Color.Transparent, RoundedCornerShape(12.dp))
                        .clickable(
                            interactionSource =  remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            selectedIndex.value = 0
                        }
                ) {
                    Text("Home", color = Color.White, modifier = Modifier.align(Alignment.Center))
                }
                Spacer(Modifier.width(separationAnim.value))
            }

            Row {
                val separationAnim = animateDpAsState(
                    targetValue = if (selectedIndex.value == 1) 20.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(Color.Transparent, RoundedCornerShape(12.dp))
                        .clickable(
                            interactionSource =  remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            selectedIndex.value = 1
                        }
                ) {
                    Text(
                        "Settings",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(Modifier.width(separationAnim.value))
            }
            Row {
                val separationAnim = animateDpAsState(
                    targetValue = if (selectedIndex.value == 2) 20.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(Color.Transparent, RoundedCornerShape(12.dp))
                        .clickable(
                            interactionSource =  remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            selectedIndex.value = 2
                        }
                ) {
                    Text(
                        "Career",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(Modifier.width(separationAnim.value))
            }
    }
    }

}

expect fun Modifier.platformBlur(
    blurRadius: Float = 16f,
    alphaMultiplier: Float = 1f,
    alphaOffset: Float = 0f
): Modifier
