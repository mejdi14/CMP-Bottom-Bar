package basic.mejdi14.component.bottombar.additional

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import basic.mejdi14.component.bottombar.icon.BasicBarIconComposable
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicItem

@Composable
internal fun BasicAdditionalItem(
    item: BasicItem?,
    config: BasicBarConfig,
    onClick: (BasicItem) -> Unit,
) {
    if (item == null) {
        Box(Modifier.size(config.itemSize))
        return
    }

    Box(
        modifier = Modifier
            .size(config.itemSize)
            .background(color = item.backgroundColor, shape = item.shape)
            .clickable(enabled = item.interaction.enabled) { onClick(item) },
    ) {
        BasicBarIconComposable(
            iconStyle = config.iconStyle,
            item = item,
            modifier = Modifier.align(Alignment.Center).size(item.size),
            isSelectedIndex = false,
        )
    }
}
