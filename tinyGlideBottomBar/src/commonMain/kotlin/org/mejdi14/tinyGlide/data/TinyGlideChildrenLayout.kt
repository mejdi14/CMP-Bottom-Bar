package org.mejdi14.tinyGlide.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Immutable
data class TinyGlideChildrenLayout(
    val itemsPerLine: Int = Int.MAX_VALUE,
    val lineSpacing: Dp = 8.dp,
    val customContentSize: DpSize = DpSize(180.dp, 48.dp),
    val customContentSpacing: Dp = 8.dp,
) {
    init {
        require(itemsPerLine > 0)
        require(lineSpacing >= 0.dp)
        require(customContentSize.width >= 0.dp)
        require(customContentSize.height >= 0.dp)
        require(customContentSpacing >= 0.dp)
    }
}
