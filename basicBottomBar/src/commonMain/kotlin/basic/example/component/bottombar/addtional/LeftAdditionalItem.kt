package basic.example.component.bottombar.addtional

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun LeftAdditionalItem(
    basicBarConfig: BasicBarConfig,
    basicItem: BasicItem?,
) {
    if (basicBarConfig.additionalItems?.leftTopItem != null) {
        Row {
            Box(
                Modifier.size(basicBarConfig.itemSize)
                    .clickable {
                        basicItem?.clickActionListener?.onItemClickListener()
                    }
                    .background(
                        color = basicItem?.backgroundColor ?: basicBarConfig.backgroundColor,
                        shape = basicItem?.itemShape ?: basicBarConfig.shape
                    )
            ) {
                if (basicItem?.icon != null) {
                    val currentAdditionalIcon = basicItem.icon
                    Icon(
                        painter = painterResource(currentAdditionalIcon.iconDrawable),
                        contentDescription = currentAdditionalIcon.contentDescription,
                        Modifier.align(Alignment.Center),
                        tint = currentAdditionalIcon.iconTintColor,
                    )
                }
            }
            Spacer(Modifier.width(basicBarConfig.spaceBetweenItems))
        }
    } else if (basicBarConfig.additionalItems?.rightBottomItem != null) {
        Spacer(Modifier.width(basicBarConfig.itemSize + basicBarConfig.spaceBetweenItems))
    }
}