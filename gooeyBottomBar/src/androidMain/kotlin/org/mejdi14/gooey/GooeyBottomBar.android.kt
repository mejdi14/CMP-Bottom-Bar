package org.mejdi14.gooey


import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer

@RequiresApi(Build.VERSION_CODES.S)
actual fun Modifier.platformBlur(
    blurRadius: Float,
    alphaMultiplier: Float,
    alphaOffset: Float
): Modifier {
    val blurEffect = RenderEffect.createBlurEffect(
        blurRadius, blurRadius, Shader.TileMode.MIRROR
    )

    val matrix = ColorMatrix().apply {
        set(
            floatArrayOf(
                1f, 0f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f, 0f,
                0f, 0f, 1f, 0f, 0f,
                0f, 0f, 0f, alphaMultiplier, alphaOffset
            )
        )
    }
    val alphaMatrix = RenderEffect.createColorFilterEffect(ColorMatrixColorFilter(matrix))

    val chainedEffect = RenderEffect.createChainEffect(alphaMatrix, blurEffect)

    return this.graphicsLayer(
        renderEffect = chainedEffect.asComposeRenderEffect()
    )
}
