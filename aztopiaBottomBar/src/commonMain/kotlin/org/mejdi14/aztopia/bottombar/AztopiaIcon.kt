package org.mejdi14.aztopia.bottombar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import org.mejdi14.aztopia.data.AztopiaItem
import org.mejdi14.aztopia.listeners.AztopiaActionListener
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AztopiaIcon(
    item: AztopiaItem,
    index: Int,
    selectedIndex: MutableState<Int?>,
    aztopiaActionListener: AztopiaActionListener,
) {
    Box(Modifier
        .clickable {
            if (item.interaction.shouldDispatchClick(selectedIndex.value, index)) {
                selectedIndex.value = item.interaction.nextSelectedIndex(selectedIndex.value, index)
                item.onClick.onClick(item, index)
                aztopiaActionListener.onClick(item, index)
            }
        }) {
        when (selectedIndex.value == index) {
            true -> {
                Icon(
                    painter = painterResource(item.icon.selectedResource),
                    contentDescription = item.icon.contentDescription,
                    tint = item.icon.selectedTint,
                    modifier = item.icon.modifier
                )
            }

            false -> {
                Icon(
                    painter = painterResource(item.icon.resource),
                    contentDescription = item.icon.contentDescription,
                    tint = item.icon.tint,
                    modifier = item.icon.modifier
                )
            }
        }
    }
}
