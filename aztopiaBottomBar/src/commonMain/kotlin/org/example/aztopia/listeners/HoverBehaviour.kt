package org.example.aztopia.listeners

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.example.aztopia.data.AztopiaItem

interface HoverBehavior {
    fun onHoverEnter(item: AztopiaItem)
    fun onHoverExit(item: AztopiaItem, scope: CoroutineScope, hoverExitJob: MutableState<Job?>)
}