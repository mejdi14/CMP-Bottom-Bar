package basic.mejdi14.component.bottombar

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal fun basicBarStripSize(itemCount: Int, itemSize: Dp, itemSpacing: Dp): Dp =
    if (itemCount <= 0) 0.dp else (itemSize * itemCount) + (itemSpacing * (itemCount - 1))

internal fun basicBarItemOffset(index: Int, itemSize: Dp, itemSpacing: Dp): Dp =
    (itemSize + itemSpacing) * index

internal fun normalizedBasicBarIndex(index: Int?, itemCount: Int): Int? =
    index?.takeIf { it in 0 until itemCount }
