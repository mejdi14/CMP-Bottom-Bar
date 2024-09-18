package org.example.tinyGlide.bottombar

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import org.example.tinyGlide.data.TinyGlideItem
import org.example.tinyGlide.data.isSelectedItem
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
                modifier = item.icon.modifier.then(modifier)
            )
        }

        false -> {
            Icon(
                painter = painterResource(item.icon.unselectedIconDrawable),
                contentDescription = item.icon.contentDescription,
                tint = item.icon.unselectedIconTint,
                modifier = item.icon.modifier.then(modifier)
            )
        }
    }
}