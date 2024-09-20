package org.example.tinyGlide.listeners

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.example.tinyGlide.data.TinyGlideItem

interface HoverBehavior {
    fun onHoverEnter(item: TinyGlideItem)
    fun onHoverExit(item: TinyGlideItem, scope: CoroutineScope, hoverExitJob: MutableState<Job?>)
}