package basic.mejdi14.component.bottombar.icon

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import basic.mejdi14.component.data.BasicBarIconStyle
import basic.mejdi14.component.data.BasicItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun BasicBarIconComposable(
    iconStyle: BasicBarIconStyle?,
    item: BasicItem,
    modifier: Modifier,
    isSelectedIndex: Boolean
) {
    Icon(
        modifier = item.icon.modifier.then(modifier.padding(item.icon.sizeReduction)),
        painter = painterResource(
            if (isSelectedIndex) item.icon.selectedResource else item.icon.resource
        ),
        contentDescription = item.icon.contentDescription,
        tint = if (isSelectedIndex) {
            iconStyle?.selectedTint ?: item.icon.selectedTint
        } else {
            iconStyle?.tint ?: item.icon.tint
        },
    )
}
