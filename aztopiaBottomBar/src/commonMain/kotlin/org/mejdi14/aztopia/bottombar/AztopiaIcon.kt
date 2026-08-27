package org.mejdi14.aztopia.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
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
    val isSelected = selectedIndex.value == index
    Box(
        modifier = Modifier
            .size(item.size)
            .background(
                color = if (isSelected) item.selectedBackgroundColor else item.backgroundColor,
                shape = item.shape,
            )
            .clickable {
                if (item.interaction.shouldDispatchClick(selectedIndex.value, index)) {
                    selectedIndex.value = item.interaction.nextSelectedIndex(selectedIndex.value, index)
                    item.onClick.onClick(item, index)
                    aztopiaActionListener.onClick(item, index)
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(if (isSelected) item.icon.selectedResource else item.icon.resource),
            contentDescription = item.icon.contentDescription,
            tint = if (isSelected) item.icon.selectedTint else item.icon.tint,
            modifier = item.icon.modifier,
        )
    }
}
