package org.example.aztopia.bottombar

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.example.aztopia.data.AztopiaItem
import org.example.aztopia.data.isSelectedItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AztopiaIcon(
    item: AztopiaItem,
    selectedItem: MutableState<AztopiaItem?>,
) {
    when (item.isSelectedItem(selectedItem.value)) {
        true -> {
            Icon(
                painter = painterResource(item.icon.selectedIconDrawable),
                contentDescription = item.icon.contentDescription,
                tint = item.icon.selectedIconTint,
                modifier = item.icon.modifier
            )
        }

        false -> {
            Icon(
                painter = painterResource(item.icon.unselectedIconDrawable),
                contentDescription = item.icon.contentDescription,
                tint = item.icon.unselectedIconTint,
                modifier = item.icon.modifier
            )
        }
    }
}