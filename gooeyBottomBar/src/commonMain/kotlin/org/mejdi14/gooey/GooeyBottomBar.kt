package org.mejdi14.gooey

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GooeyBottomBar(
    modifier: Modifier = Modifier,
    separationDp: Float,
    itemWidth: Dp = 100.dp,
    itemHeight: Dp = 50.dp,
    cornerRadius: Dp = 16.dp,
    selectedColor: Color = Color(0xFF1279FF),
    unselectedColor: Color = Color.Black,
    labels: List<String> = listOf("Home", "Settings", "Career"),
) {
    require(separationDp >= 0f) { "separationDp cannot be negative" }
    require(itemWidth > 0.dp) { "itemWidth must be greater than zero" }
    require(itemHeight > 0.dp) { "itemHeight must be greater than zero" }
    if (labels.isEmpty()) return

    val selectedIndex = remember { mutableStateOf(0) }
    Box(modifier = modifier) {
        GooeyItems(
            labels = labels,
            selectedIndex = selectedIndex,
            separation = separationDp.dp,
            itemWidth = itemWidth,
            itemHeight = itemHeight,
            selectedColor = selectedColor,
            unselectedColor = unselectedColor,
            cornerRadius = cornerRadius,
            showLabels = false,
            modifier = Modifier
                .clip(RoundedCornerShape(cornerRadius))
                .platformBlur(blurRadius = 55f, alphaMultiplier = 50f, alphaOffset = -5000f)
                .padding(10.dp),
        )
        GooeyItems(
            labels = labels,
            selectedIndex = selectedIndex,
            separation = separationDp.dp,
            itemWidth = itemWidth,
            itemHeight = itemHeight,
            selectedColor = selectedColor,
            unselectedColor = unselectedColor,
            cornerRadius = cornerRadius,
            showLabels = true,
            modifier = Modifier.padding(10.dp),
        )
    }
}

@Composable
private fun GooeyItems(
    labels: List<String>,
    selectedIndex: MutableState<Int>,
    separation: Dp,
    itemWidth: Dp,
    itemHeight: Dp,
    selectedColor: Color,
    unselectedColor: Color,
    cornerRadius: Dp,
    showLabels: Boolean,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        labels.forEachIndexed { index, label ->
            val animatedSeparation = animateDpAsState(
                targetValue = if (selectedIndex.value == index) separation else 0.dp,
                animationSpec = spring(),
                label = "Gooey item spacing",
            )
            Spacer(Modifier.width(animatedSeparation.value))
            Box(
                modifier = Modifier
                    .size(width = itemWidth, height = itemHeight)
                    .background(
                        color = if (showLabels) {
                            Color.Transparent
                        } else if (selectedIndex.value == index) {
                            selectedColor
                        } else {
                            unselectedColor
                        },
                        shape = RoundedCornerShape(cornerRadius * 0.75f),
                    )
                    .then(
                        if (showLabels) {
                            Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { selectedIndex.value = index }
                        } else {
                            Modifier
                        },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                if (showLabels) {
                    Text(text = label, color = Color.White)
                }
            }
            Spacer(Modifier.width(animatedSeparation.value))
        }
    }
}

expect fun Modifier.platformBlur(
    blurRadius: Float = 16f,
    alphaMultiplier: Float = 1f,
    alphaOffset: Float = 0f,
): Modifier
