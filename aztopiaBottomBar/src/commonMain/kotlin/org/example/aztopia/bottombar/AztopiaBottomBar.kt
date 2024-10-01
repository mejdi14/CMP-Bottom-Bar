package org.example.aztopia.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
    val spreadOut = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf<AztopiaItem?>(null) }
    BoxWithConstraints(
        modifier = parentModifier.fillMaxWidth().height(100.dp)
            .background(Color.White, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))

    ) {
        val parentMaxWidth = maxWidth
        val parentMaxHeight = maxHeight
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = parentModifier.fillMaxWidth().height(70.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                bottomBarItems.filterIndexed { index, _ -> index % 2 == 0 }
                    .forEach { item ->
                        AztopiaIcon(item, selectedItem)
                    }
            }

            Spacer(Modifier.width(60.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                bottomBarItems.filterIndexed { index, _ -> index % 2 != 0 }
                    .forEach { item ->
                        AztopiaIcon(item, selectedItem)
                    }
            }
        }
        AztopiaAnimatedCircles(parentMaxWidth, parentMaxHeight, spreadOut)
    }
}
