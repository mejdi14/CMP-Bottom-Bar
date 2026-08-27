package basic.mejdi14.component.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import basic.mejdi14.component.bottombar.icon.BasicBarIconComposable
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicItem

@Composable
internal fun BasicBarItemBackground(
    item: BasicItem,
    isSelected: Boolean,
    isHovered: Boolean,
    config: BasicBarConfig,
    itemSize: Dp,
) {
    val color = when {
        isSelected -> item.selectedBackgroundColor
        isHovered -> config.hoverColor
        else -> item.backgroundColor
    }
    Box(basicBarItemSlotModifier(config, itemSize)) {
        Box(
            Modifier
                .align(Alignment.Center)
                .size(itemSize)
                .background(color = color, shape = item.shape)
        )
    }
}

@Composable
internal fun BasicBarItemForeground(
    item: BasicItem,
    index: Int,
    isSelected: Boolean,
    config: BasicBarConfig,
    itemSize: Dp,
    onHover: (Int, Boolean) -> Unit,
    onClick: (Int) -> Unit,
) {
    Box(basicBarItemSlotModifier(config, itemSize)) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(itemSize)
                .hoverEffect { onHover(index, it) }
                .clickable(
                    enabled = item.interaction.enabled,
                    interactionSource = remember(item) { MutableInteractionSource() },
                    indication = null,
                ) { onClick(index) },
        ) {
            BasicBarIconComposable(
                iconStyle = config.iconStyle,
                item = item,
                modifier = Modifier.align(Alignment.Center).size(item.size),
                isSelectedIndex = isSelected,
            )
        }
    }
}

private fun basicBarItemSlotModifier(
    config: BasicBarConfig,
    itemSize: Dp,
): Modifier = if (config.position.isHorizontal) {
    Modifier.width(config.itemSize).height(itemSize)
} else {
    Modifier.width(itemSize).height(config.itemSize)
}
