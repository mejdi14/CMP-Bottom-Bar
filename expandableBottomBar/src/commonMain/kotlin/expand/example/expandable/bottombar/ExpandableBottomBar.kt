package expand.mejdi14.expandable.bottombar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.mejdi14.core.bottombar.data.BottomBarItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun ExpandableBottomBar(
    bottomBarItems: List<BottomBarItem>,
    parentModifier: Modifier,
    onIconClick: (BottomBarItem) -> Unit,
    config: ExpandableBarConfig = ExpandableBarConfig(),
) {
    if (bottomBarItems.isEmpty()) return

    val selectedIndex = remember { mutableStateOf(0) }
    val animatedOffset = animateDpAsState(
        targetValue = config.itemSize * selectedIndex.value,
        label = "Expandable bottom bar indicator",
    )
    val contentWidth = config.width - (config.outerPadding * 2)
    val occupiedWidth = config.itemSize * bottomBarItems.size
    val remainingWidth = contentWidth - occupiedWidth
    val spaceBetween = if (remainingWidth > 0.dp) {
        remainingWidth / (bottomBarItems.size + 1)
    } else {
        0.dp
    }

    Box(
        modifier = parentModifier
            .width(config.width)
            .background(config.containerColor, config.shape)
            .padding(config.outerPadding),
    ) {
        Column(Modifier.fillMaxWidth()) {
            repeat(config.rowCount) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(config.rowHeight),
                ) {
                    CustomBottomBar(
                        config = config.indicator,
                        color = config.indicator.color,
                        animatedOffset = animatedOffset,
                        spaceBetween = spaceBetween,
                        selectedIndex = selectedIndex,
                        itemSize = config.itemSize,
                        rowHeight = config.rowHeight,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        bottomBarItems.forEachIndexed { index, item ->
                            IconButton(
                                onClick = {
                                    if (item.interaction.shouldDispatchClick(selectedIndex.value, index)) {
                                        selectedIndex.value = item.interaction
                                            .nextSelectedIndex(selectedIndex.value, index)
                                            ?: selectedIndex.value
                                        onIconClick(item)
                                    }
                                },
                                modifier = Modifier.size(config.itemSize),
                            ) {
                                Icon(
                                    painter = painterResource(item.icon.selectedResource),
                                    contentDescription = item.icon.contentDescription,
                                    tint = config.iconColor,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
