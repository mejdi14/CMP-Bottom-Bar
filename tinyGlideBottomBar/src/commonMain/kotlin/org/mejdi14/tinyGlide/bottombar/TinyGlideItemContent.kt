package org.mejdi14.tinyGlide.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideItemPosition
import org.mejdi14.tinyGlide.data.TinyGlideItemVisualState

typealias TinyGlideItemContent = @Composable BoxScope.(
    item: TinyGlideItem,
    position: TinyGlideItemPosition,
    visualState: TinyGlideItemVisualState,
) -> Unit

typealias TinyGlideCustomChildrenContent = @Composable BoxScope.(
    parentItem: TinyGlideItem,
    parentPosition: TinyGlideItemPosition,
) -> Unit

@Composable
fun BoxScope.TinyGlideDefaultParentContent(
    item: TinyGlideItem,
    visualState: TinyGlideItemVisualState,
) {
    TinyGlideIcon(
        item = item,
        isActive = visualState.isExpanded,
        modifier = Modifier,
        displaySize = visualState.displaySize - if (item.decoration.label == null) 0.dp else 12.dp,
    )
    TinyGlideDefaultDecorations(item, visualState)
}

@Composable
fun BoxScope.TinyGlideDefaultChildContent(
    item: TinyGlideItem,
    visualState: TinyGlideItemVisualState,
) {
    TinyGlideIcon(
        item = item,
        isActive = true,
        modifier = Modifier,
        displaySize = visualState.displaySize - if (item.decoration.label == null) 0.dp else 10.dp,
    )
    TinyGlideDefaultDecorations(item, visualState)
}

@Composable
fun BoxScope.TinyGlideDefaultDecorations(
    item: TinyGlideItem,
    visualState: TinyGlideItemVisualState,
) {
    val decoration = item.decoration
    decoration.label?.let { label ->
        Text(
            text = label,
            color = decoration.contentColor,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 4.dp, vertical = 2.dp),
        )
    }
    decoration.badge?.let { badge ->
        if (badge.isEmpty()) {
            androidx.compose.foundation.layout.Box(
                Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 3.dp, y = (-3).dp)
                    .defaultMinSize(minWidth = 10.dp, minHeight = 10.dp)
                    .background(decoration.badgeContainerColor, CircleShape),
            )
        } else {
            Text(
                text = badge,
                color = decoration.badgeContentColor,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 5.dp, y = (-5).dp)
                    .defaultMinSize(minWidth = 16.dp, minHeight = 16.dp)
                    .background(decoration.badgeContainerColor, CircleShape)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
            )
        }
    }
    decoration.tooltip
        ?.takeIf { visualState.isHovered || visualState.isFocused }
        ?.let { tooltip ->
            Text(
                text = tooltip,
                color = decoration.tooltipContentColor,
                fontSize = 11.sp,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-34).dp)
                    .zIndex(2f)
                    .background(decoration.tooltipContainerColor, RoundedCornerShape(7.dp))
                    .padding(horizontal = 8.dp, vertical = 5.dp),
            )
        }
}
