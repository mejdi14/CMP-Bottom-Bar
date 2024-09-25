package org.example.aztopia.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import org.example.aztopia.data.AztopiaItem
import org.example.aztopia.listeners.AztopiaActionListener


@Composable
fun AztopiaBottomBar(
    bottomBarItems: List<AztopiaItem>,
    parentModifier: Modifier,
    aztopiaActionListener: AztopiaActionListener
) {
    val selectedIndex = remember { mutableStateOf<Int?>(null) }
    val lazyListState = rememberLazyListState()
    val selectedItem = remember { mutableStateOf<AztopiaItem?>(null) }
    val hoverExitJob = remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()
    val isHovering = remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = parentModifier.fillMaxWidth().height(60.dp)
    ) {
        Row (horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f).background(Color.Blue).fillMaxHeight()){
            bottomBarItems.filterIndexed { index, _ -> index % 2 == 0 }
                .forEach { item ->
                    AztopiaIcon(item, selectedItem)
                }
        }

        Row (horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f).fillMaxHeight()){
            bottomBarItems.filterIndexed { index, _ -> index % 2 != 0 }
                .forEach { item ->
                    AztopiaIcon(item, selectedItem)
                }
        }
    }
}
