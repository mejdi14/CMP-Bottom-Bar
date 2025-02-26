package org.mejdi14.tinyGlide.bottombar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.isSelectedItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun TinyGlideIcon(
    item: TinyGlideItem,
    selectedItem: MutableState<TinyGlideItem?>,
    modifier: Modifier
) {
    when (item.isSelectedItem(selectedItem.value)) {
        true -> {
            Icon(
                painter = painterResource(item.icon.selectedIconDrawable),
                contentDescription = item.icon.contentDescription,
                tint = item.icon.selectedIconTint,
                modifier = item.icon.modifier.then(modifier.size(item.size - item.icon.sizeDifferenceComparedToParent))
            )
        }

        false -> {
            Icon(
                painter = painterResource(item.icon.iconDrawable),
                contentDescription = item.icon.contentDescription,
                tint = item.icon.iconTintColor,
                modifier = item.icon.modifier.then(modifier.size(item.size - item.icon.sizeDifferenceComparedToParent))
            )
        }
    }
}