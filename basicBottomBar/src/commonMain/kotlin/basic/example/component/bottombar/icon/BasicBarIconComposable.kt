package basic.example.component.bottombar.icon

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import basic.example.component.data.BasicItem
import org.example.core.bottombar.data.GlobalBottomBarIcon
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun BasicBarIconComposable(
    globalIconConfig: GlobalBottomBarIcon?,
    item: BasicItem,
    modifier: Modifier,
    isSelectedIndex: Boolean
) {
        Icon(
            modifier = modifier.padding(item.icon.sizeDifferenceComparedToParent),
            painter = painterResource(
                if (isSelectedIndex) item.icon.selectedIconDrawable else
                    item.icon.iconDrawable
            ),
            contentDescription = item.contentDescription,
            tint = if (isSelectedIndex) (globalIconConfig?.selectedIconTintColor
                ?: item.icon.selectedIconTint) else (globalIconConfig?.iconTintColor
                ?: item.icon.iconTintColor),
        )
}