package basic.example.component.bottombar.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import basic.example.component.data.BasicItem
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun BasicBarIconComposable(item: BasicItem, modifier: Modifier) {
    Icon(
        painter = painterResource(item.icon.selectedIconDrawable),
        contentDescription = item.contentDescription,
        tint = item.icon.iconTintColor,
        modifier = modifier
    )
}