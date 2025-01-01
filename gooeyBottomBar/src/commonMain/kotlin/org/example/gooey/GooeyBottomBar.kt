package org.example.gooey

import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
    // Combine everything into one layer so the blur merges them:
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            // Increase blurRadius and alphaMultiplier for a more pronounced goo effect.
            .platformBlur(blurRadius = 50f, alphaMultiplier = 40f, alphaOffset = -2000f)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(separationDp.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 80.dp, height = 50.dp)
                .background(Color.Blue, RoundedCornerShape(12.dp))
        )

        Box(
            modifier = Modifier
                .size(width = 80.dp, height = 50.dp)
                .background(Color.Blue, RoundedCornerShape(12.dp))
        )
        Box(
            modifier = Modifier
                .size(width = 80.dp, height = 50.dp)
                .background(Color.Blue, RoundedCornerShape(12.dp))
        )
    }
}

expect fun Modifier.platformBlur(
    blurRadius: Float = 16f,
    alphaMultiplier: Float = 1f,
    alphaOffset: Float = 0f
): Modifier
