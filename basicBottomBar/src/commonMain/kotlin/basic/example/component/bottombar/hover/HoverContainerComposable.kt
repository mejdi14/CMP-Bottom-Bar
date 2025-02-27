package basic.example.component.bottombar.hover

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import basic.mejdi14.component.bottombar.hover.HoverDescriptionTextComposable
import basic.mejdi14.component.data.BasicBarConfig
import basic.mejdi14.component.data.BasicItem

@Composable
internal fun HoverContainerComposable(
    basicBarConfig: BasicBarConfig,
    spaceBetween: MutableState<Dp>,
    hoverSelectedIndex: MutableState<Int>,
    bottomBarItems: List<BasicItem>,
    isHovered: MutableState<Boolean>
) {
    Box(Modifier.padding(basicBarConfig.basicBarPadding)) {
        HoverDescriptionTextComposable(
            spaceBetween.value,
            hoverSelectedIndex,
            bottomBarItems,
            isHovered,
            basicBarConfig
        )
    }
}