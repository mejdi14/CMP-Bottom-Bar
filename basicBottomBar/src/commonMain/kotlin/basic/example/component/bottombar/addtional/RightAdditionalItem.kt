package basic.example.component.bottombar.addtional

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicBarAdditionalItems
import basic.mejdi14.component.data.BasicItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun RightAdditionalItem(
    additionalItems: BasicBarAdditionalItems?,
    basicBarConfig: BasicBarConfig,
    basicItem: BasicItem?,
) {
    if (additionalItems?.endItem != null) {
        Row {
            Spacer(Modifier.width(basicBarConfig.spaceBetweenItems))
            Box(
                Modifier.size(basicBarConfig.itemSize + basicBarConfig.aroundItemsPadding)
                    .clickable {
                        basicItem?.takeIf { it.interaction.enabled }
                            ?.let { it.onClick.onClick(it, null) }
                    }
                    .background(
                        color = basicItem?.backgroundColor ?: basicBarConfig.backgroundColor,
                        shape = basicItem?.shape ?: basicBarConfig.shape
                    )
            ) {
                if (basicItem?.icon != null) {


                    val currentAdditionalIcon =
                        basicItem.icon

                    Icon(
                        painter = painterResource(currentAdditionalIcon.resource),
                        contentDescription = currentAdditionalIcon.contentDescription,
                        Modifier.align(Alignment.Center).size(basicItem.size)
                            .padding(basicItem.icon.sizeReduction),
                        tint = currentAdditionalIcon.tint,
                    )
                }
            }
        }
    } else if (basicBarConfig.additionalItems?.startItem != null) {
        Spacer(Modifier.width(basicBarConfig.itemSize + basicBarConfig.spaceBetweenItems))
    }
}
