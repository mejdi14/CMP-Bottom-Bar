package basic.mejdi14.component.bottombar

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
actual fun Modifier.hoverEffect(onHover: (Boolean) -> Unit): Modifier = this.then(
    Modifier.onPointerEvent(PointerEventType.Enter){onHover(true)}
        .onPointerEvent(PointerEventType.Exit){onHover(false)}
)