package org.mejdi14.tinyGlide.bottombar

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.enum.TinyGlideChildrenPlacement
import org.mejdi14.tinyGlide.enum.TinyGlideOrientation
import org.mejdi14.tinyGlide.enum.TinyGlideVerticalSide

internal data class TinyGlideGroupOffset(
    val x: Dp,
    val y: Dp,
)

internal data class TinyGlideGridSize(
    val width: Dp,
    val height: Dp,
)

internal fun tinyGlideGridSize(
    items: List<TinyGlideItem>,
    itemsPerLine: Int,
    childrenAreHorizontal: Boolean,
    lineSpacing: Dp,
): TinyGlideGridSize {
    if (items.isEmpty()) {
        return TinyGlideGridSize(0.dp, 0.dp)
    }
    val lines = items.chunked(itemsPerLine.coerceAtLeast(1))
    return if (childrenAreHorizontal) {
        TinyGlideGridSize(
            width = lines.maxOf { line ->
                line.fold(0.dp) { width, item ->
                    width + (item.subItemSize?.width ?: item.size) +
                        (item.itemSeparationSpace * 2)
                }
            },
            height = lines.foldIndexed(0.dp) { index, height, line ->
                height +
                    (line.maxOfOrNull { it.subItemSize?.height ?: it.size } ?: 0.dp) +
                    if (index == 0) 0.dp else lineSpacing
            },
        )
    } else {
        TinyGlideGridSize(
            width = lines.foldIndexed(0.dp) { index, width, line ->
                width +
                    (line.maxOfOrNull { it.subItemSize?.width ?: it.size } ?: 0.dp) +
                    if (index == 0) 0.dp else lineSpacing
            },
            height = lines.maxOf { line ->
                line.fold(0.dp) { height, item ->
                    height + (item.subItemSize?.height ?: item.size) +
                        (item.itemSeparationSpace * 2)
                }
            },
        )
    }
}

internal fun resolveTinyGlideChildrenPlacement(
    placement: TinyGlideChildrenPlacement,
    orientation: TinyGlideOrientation,
    verticalSide: TinyGlideVerticalSide,
): TinyGlideChildrenPlacement = when (placement) {
    TinyGlideChildrenPlacement.AUTO -> when (orientation) {
        TinyGlideOrientation.HORIZONTAL -> TinyGlideChildrenPlacement.ABOVE
        TinyGlideOrientation.VERTICAL -> when (verticalSide) {
            TinyGlideVerticalSide.START -> TinyGlideChildrenPlacement.END
            TinyGlideVerticalSide.END -> TinyGlideChildrenPlacement.START
        }
    }

    else -> placement
}

internal fun tinyGlideGroupOffset(
    placement: TinyGlideChildrenPlacement,
    childrenAreOnLeft: Boolean,
    parentLeft: Dp,
    parentTop: Dp,
    parentRight: Dp,
    parentBottom: Dp,
    parentCenterX: Dp,
    parentCenterY: Dp,
    parentGap: Dp,
    groupWidth: Dp,
    groupHeight: Dp,
    containerWidth: Dp,
    containerHeight: Dp,
    edgePadding: Dp,
): TinyGlideGroupOffset {
    val x = when (placement) {
        TinyGlideChildrenPlacement.ABOVE,
        TinyGlideChildrenPlacement.BELOW,
        -> keepGroupInsideContainer(
            preferredOffset = parentCenterX - (groupWidth / 2),
            groupSize = groupWidth,
            containerSize = containerWidth,
            edgePadding = edgePadding,
        )

        TinyGlideChildrenPlacement.START,
        TinyGlideChildrenPlacement.END,
        -> if (childrenAreOnLeft) {
            parentLeft - parentGap - groupWidth
        } else {
            parentRight + parentGap
        }

        TinyGlideChildrenPlacement.AUTO -> 0.dp
    }
    val y = when (placement) {
        TinyGlideChildrenPlacement.ABOVE -> parentTop - parentGap - groupHeight
        TinyGlideChildrenPlacement.BELOW -> parentBottom + parentGap
        TinyGlideChildrenPlacement.START,
        TinyGlideChildrenPlacement.END,
        -> keepGroupInsideContainer(
            preferredOffset = parentCenterY - (groupHeight / 2),
            groupSize = groupHeight,
            containerSize = containerHeight,
            edgePadding = edgePadding,
        )

        TinyGlideChildrenPlacement.AUTO -> 0.dp
    }
    return TinyGlideGroupOffset(x, y)
}

internal fun keepGroupInsideContainer(
    preferredOffset: Dp,
    groupSize: Dp,
    containerSize: Dp,
    edgePadding: Dp,
): Dp {
    val maximumOffset = containerSize - groupSize - edgePadding
    return if (maximumOffset >= edgePadding) {
        preferredOffset.coerceIn(edgePadding, maximumOffset)
    } else {
        ((containerSize - groupSize) / 2).coerceAtLeast(0.dp)
    }
}
