package basic.example.component.bottombar.hover

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basic.mejdi14.component.bottombar.hover.HoverDescriptionTextComposable
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicItem

@Composable
internal fun BottomHoverComposable(
    spaceBetween: MutableState<Dp>,
    hoverSelectedIndex: MutableState<Int>,
    bottomBarItems: List<BasicItem>,
    isHovered: MutableState<Boolean>,
    basicBarConfig: BasicBarConfig
) {
    Box(Modifier.padding(5.dp)) {
        HoverDescriptionTextComposable(
            spaceBetween.value,
            hoverSelectedIndex,
            bottomBarItems,
            isHovered,
            basicBarConfig
        )
    }
}