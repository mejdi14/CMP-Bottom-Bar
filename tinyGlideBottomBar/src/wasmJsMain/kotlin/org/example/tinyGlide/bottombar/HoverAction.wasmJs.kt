package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event


@OptIn(ExperimentalComposeUiApi::class)
actual fun Modifier.hoverEffect(onHover: (Boolean) -> Unit): Modifier = this.then(
    Modifier.onPointerEvent(PointerEventType.Enter){onHover(true)}
    .onPointerEvent(PointerEventType.Exit){onHover(false)}
)
