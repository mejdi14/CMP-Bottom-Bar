package org.mejdi14.project.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.calendar_day
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.papers
import kmp_bottom_bar.composeapp.generated.resources.the_plus_icon
import org.mejdi14.aztopia.bottombar.AztopiaBottomBar
import org.mejdi14.aztopia.data.AztopiaAnimatedCircle
import org.mejdi14.aztopia.data.AztopiaAnimatedComposable
import org.mejdi14.aztopia.data.AztopiaItem
import org.mejdi14.aztopia.helper.AztopiaTrio
import org.mejdi14.aztopia.listeners.AztopiaActionListener
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun AztopiaDemo(modifier: Modifier = Modifier) {
    var itemSize by remember { mutableStateOf(46f) }
    var actionSize by remember { mutableStateOf(76f) }
    var actionRadius by remember { mutableStateOf(34f) }
    var itemCount by remember { mutableStateOf(4) }
    var palette by remember { mutableStateOf("Colorful") }

    val colorful = palette == "Colorful"
    val items = remember(itemSize, itemCount, colorful) {
        aztopiaDemoItems(itemSize.dp, colorful).take(itemCount)
    }
    val centerAction = remember(actionSize, actionRadius, colorful) {
        aztopiaCenterAction(actionSize.dp, actionRadius.dp, colorful)
    }

    Box(modifier.fillMaxSize()) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 112.dp),
        ) {
            PlaygroundSlider(
                label = "Navigation size",
                value = itemSize,
                valueRange = 36f..56f,
                onValueChange = { itemSize = it },
            )
            PlaygroundSlider(
                label = "Center action size",
                value = actionSize,
                valueRange = 62f..88f,
                onValueChange = { actionSize = it },
            )
            PlaygroundSlider(
                label = "Action spread",
                value = actionRadius,
                valueRange = 22f..54f,
                onValueChange = { actionRadius = it },
            )
            PlaygroundOptions(
                label = "Navigation items",
                options = listOf("2", "4"),
                selected = itemCount.toString(),
                onSelected = { itemCount = it.toInt() },
            )
            PlaygroundOptions(
                label = "Palette",
                options = listOf("Colorful", "Minimal"),
                selected = palette,
                onSelected = { palette = it },
            )
        }

        AztopiaBottomBar(
            bottomBarItems = items,
            aztopiaAnimatedComposable = centerAction,
            baseModifier = Modifier.align(Alignment.BottomCenter),
            aztopiaActionListener = object : AztopiaActionListener {
                override fun onClick(item: AztopiaItem, index: Int?) = Unit

                override fun onAnimatedCircularItemClickListener(index: Int) = Unit
            },
        )
    }
}

private fun aztopiaDemoItems(size: Dp, colorful: Boolean): List<AztopiaItem> {
    val resources = listOf(
        Res.drawable.home_line to "Home",
        Res.drawable.papers to "Library",
        Res.drawable.calendar_day to "Calendar",
        Res.drawable.menu_meatballs to "More",
    )
    val backgrounds = if (colorful) {
        listOf(
            Color(0xFFCDC1FF),
            Color(0xFFE6D9A2),
            Color(0xFFAAB396),
            Color(0xFFFFAF00),
        )
    } else {
        List(4) { Color(0xFFE4E4E7) }
    }
    return resources.mapIndexed { index, (resource, description) ->
        aztopiaItem(
            resource = resource,
            contentDescription = description,
            size = size,
            backgroundColor = backgrounds[index],
        )
    }
}

private fun aztopiaItem(
    resource: DrawableResource,
    contentDescription: String,
    size: Dp,
    backgroundColor: Color,
): AztopiaItem = AztopiaItem(
    icon = BottomBarIcon(
        resource = resource,
        tint = Color(0xFF27272A),
        selectedTint = Color.Black,
        contentDescription = contentDescription,
    ),
    size = size,
    backgroundColor = backgroundColor,
    selectedBackgroundColor = backgroundColor.copy(alpha = 0.68f),
)

private fun aztopiaCenterAction(
    size: Dp,
    radius: Dp,
    colorful: Boolean,
): AztopiaAnimatedComposable {
    val colors = if (colorful) {
        listOf(
            Color(0xFF7C3AED),
            Color(0xFFEC4899),
            Color(0xFF10B981),
            Color(0xFFF59E0B),
        )
    } else {
        listOf(
            Color(0xFF18181B),
            Color(0xFF3F3F46),
            Color(0xFF52525B),
            Color(0xFF71717A),
        )
    }
    return AztopiaAnimatedComposable(
        icon = BottomBarIcon(
            resource = Res.drawable.the_plus_icon,
            tint = Color.White,
            selectedTint = Color.White,
            contentDescription = "Open quick actions",
        ),
        size = size,
        circularMovementRadius = radius,
        backgroundColor = colors[0],
        animatedCircleItems = AztopiaTrio(
            AztopiaAnimatedCircle(
                backgroundColor = colors[1],
                icon = BottomBarIcon(Res.drawable.open_reader, tint = Color.White),
            ),
            AztopiaAnimatedCircle(
                backgroundColor = colors[2],
                icon = BottomBarIcon(Res.drawable.calendar_day, tint = Color.White),
            ),
            AztopiaAnimatedCircle(
                backgroundColor = colors[3],
                icon = BottomBarIcon(Res.drawable.papers, tint = Color.White),
            ),
        ),
    )
}
