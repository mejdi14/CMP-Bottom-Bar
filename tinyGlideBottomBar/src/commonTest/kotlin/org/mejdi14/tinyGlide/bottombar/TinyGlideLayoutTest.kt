package org.mejdi14.tinyGlide.bottombar

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.DpSize
import kotlin.test.Test
import kotlin.test.assertEquals
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.enum.TinyGlideChildrenPlacement
import org.mejdi14.tinyGlide.enum.TinyGlideOrientation
import org.mejdi14.tinyGlide.enum.TinyGlideVerticalSide

@OptIn(InternalResourceApi::class)
class TinyGlideLayoutTest {
    @Test
    fun automaticPlacementOpensInwardForEveryOrientation() {
        assertEquals(
            TinyGlideChildrenPlacement.ABOVE,
            resolveTinyGlideChildrenPlacement(
                TinyGlideChildrenPlacement.AUTO,
                TinyGlideOrientation.HORIZONTAL,
                TinyGlideVerticalSide.END,
            ),
        )
        assertEquals(
            TinyGlideChildrenPlacement.START,
            resolveTinyGlideChildrenPlacement(
                TinyGlideChildrenPlacement.AUTO,
                TinyGlideOrientation.VERTICAL,
                TinyGlideVerticalSide.END,
            ),
        )
        assertEquals(
            TinyGlideChildrenPlacement.END,
            resolveTinyGlideChildrenPlacement(
                TinyGlideChildrenPlacement.AUTO,
                TinyGlideOrientation.VERTICAL,
                TinyGlideVerticalSide.START,
            ),
        )
    }

    @Test
    fun horizontalChildrenAreClampedInsideContainerEdges() {
        val offset = groupOffset(
            placement = TinyGlideChildrenPlacement.ABOVE,
            parentCenterX = 20.dp,
            parentCenterY = 80.dp,
        )

        assertEquals(8.dp, offset.x)
        assertEquals(13.dp, offset.y)
    }

    @Test
    fun verticalChildrenStayCenteredAndOpenBesideParent() {
        val offset = groupOffset(
            placement = TinyGlideChildrenPlacement.START,
            parentCenterX = 170.dp,
            parentCenterY = 20.dp,
        )

        assertEquals(73.dp, offset.x)
        assertEquals(8.dp, offset.y)
    }

    @Test
    fun horizontalChildrenWrapIntoRows() {
        val size = tinyGlideGridSize(
            items = children(6),
            itemsPerLine = 3,
            childrenAreHorizontal = true,
            lineSpacing = 8.dp,
        )

        assertEquals(150.dp, size.width)
        assertEquals(88.dp, size.height)
    }

    @Test
    fun verticalChildrenWrapIntoColumns() {
        val size = tinyGlideGridSize(
            items = children(6),
            itemsPerLine = 3,
            childrenAreHorizontal = false,
            lineSpacing = 8.dp,
        )

        assertEquals(88.dp, size.width)
        assertEquals(150.dp, size.height)
    }

    @Test
    fun rectangularCustomChildSharesALineWithDefaultChild() {
        val items = children(2).toMutableList()
        items[0] = items[0].copy(subItemSize = DpSize(120.dp, 50.dp))

        val size = tinyGlideGridSize(
            items = items,
            itemsPerLine = 3,
            childrenAreHorizontal = true,
            lineSpacing = 8.dp,
        )

        assertEquals(180.dp, size.width)
        assertEquals(50.dp, size.height)
    }

    private fun groupOffset(
        placement: TinyGlideChildrenPlacement,
        parentCenterX: androidx.compose.ui.unit.Dp,
        parentCenterY: androidx.compose.ui.unit.Dp,
    ): TinyGlideGroupOffset = tinyGlideGroupOffset(
        placement = placement,
        childrenAreOnLeft = true,
        parentLeft = parentCenterX - 25.dp,
        parentTop = parentCenterY - 25.dp,
        parentRight = parentCenterX + 25.dp,
        parentBottom = parentCenterY + 25.dp,
        parentCenterX = parentCenterX,
        parentCenterY = parentCenterY,
        parentGap = 12.dp,
        groupWidth = 60.dp,
        groupHeight = 30.dp,
        containerWidth = 200.dp,
        containerHeight = 200.dp,
        edgePadding = 8.dp,
    )

    private fun children(count: Int): List<TinyGlideItem> = List(count) { index ->
        TinyGlideItem(
            key = "child-$index",
            icon = BottomBarIcon(
                resource = DrawableResource("child-$index", emptySet()),
                contentDescription = "Child $index",
            ),
            size = 40.dp,
            itemSeparationSpace = 5.dp,
        )
    }
}
