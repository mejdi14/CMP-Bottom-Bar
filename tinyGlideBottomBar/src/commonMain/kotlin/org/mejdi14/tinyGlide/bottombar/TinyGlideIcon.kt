package org.mejdi14.tinyGlide.bottombar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun TinyGlideIcon(
    item: TinyGlideItem,
    isActive: Boolean,
    modifier: Modifier,
    displaySize: Dp = item.size,
) {
    when (isActive) {
        true -> {
            Icon(
                painter = painterResource(item.icon.selectedResource),
                contentDescription = item.icon.contentDescription,
                tint = item.icon.selectedTint,
                modifier = item.icon.modifier.then(modifier.size(displaySize - item.icon.sizeReduction))
            )
        }

        false -> {
            Icon(
                painter = painterResource(item.icon.resource),
                contentDescription = item.icon.contentDescription,
                tint = item.icon.tint,
                modifier = item.icon.modifier.then(modifier.size(displaySize - item.icon.sizeReduction))
            )
        }
    }
}
