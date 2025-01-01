package org.example.gooey

import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
                    targetValue = if (selectedIndex.value == 0) 10.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(Color.Blue)

                )
                Spacer(Modifier.width(separationAnim.value))
            }

            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 50.dp)
                    .background(Color.Blue)
            ){
            }
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 50.dp)
                    .background(Color.Blue)
            ){
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
                    targetValue = if (selectedIndex.value == 0) 10.dp else 0.dp,
                    animationSpec = spring()
                )
                Spacer(Modifier.width(separationAnim.value))
                Box(
                    modifier = Modifier
                        .size(width = 100.dp, height = 50.dp)
                        .background(Color.Transparent, RoundedCornerShape(12.dp))
                ) {
                    Text("Home", color = Color.White, modifier = Modifier.align(Alignment.Center))
                }
                Spacer(Modifier.width(separationAnim.value))
            }

        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 50.dp)
                .background(Color.Transparent, RoundedCornerShape(12.dp))
        ){
            Text("Settings", color = Color.White, modifier = Modifier.align(Alignment.Center))
        }
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 50.dp)
                .background(Color.Transparent, RoundedCornerShape(12.dp))
                .clickable {

                }
        ){
            Text("Career", color = Color.White, modifier = Modifier.align(Alignment.Center))
        }
    }
    }

}

expect fun Modifier.platformBlur(
    blurRadius: Float = 16f,
    alphaMultiplier: Float = 1f,
    alphaOffset: Float = 0f
): Modifier
