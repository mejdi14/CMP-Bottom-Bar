package org.mejdi14.tinyGlide.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Stable
class TinyGlideState internal constructor() {
    internal val selectedIndex = mutableStateOf<Int?>(null)
    internal val selectedItem = mutableStateOf<TinyGlideItem?>(null)

    fun dismiss() {
        selectedItem.value?.let { item ->
            item.parentItemDynamicSize.value = item.size
        }
        selectedIndex.value = null
        selectedItem.value = null
    }
}

@Composable
fun rememberTinyGlideState(): TinyGlideState = remember { TinyGlideState() }
