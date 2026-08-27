package org.mejdi14.core.bottombar.indicator

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class BottomBarIndicatorConfigTest {
    @Test
    fun defaultsDescribeAnEndAlignedSquare() {
        val config = BottomBarIndicatorConfig()

        assertEquals(Color.Blue, config.color)
        assertEquals(BottomBarIndicatorShape.SQUARE, config.shapeType)
        assertEquals(BottomBarIndicatorPosition.END, config.position)
        assertEquals(6.dp, config.thickness)
        assertEquals(3.dp, config.padding)
    }
}
