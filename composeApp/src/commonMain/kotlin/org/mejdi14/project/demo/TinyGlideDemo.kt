package org.mejdi14.project.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.launch
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.icon1
import kmp_bottom_bar.composeapp.generated.resources.icon10
import kmp_bottom_bar.composeapp.generated.resources.icon11
import kmp_bottom_bar.composeapp.generated.resources.icon12
import kmp_bottom_bar.composeapp.generated.resources.icon13
import kmp_bottom_bar.composeapp.generated.resources.icon2
import kmp_bottom_bar.composeapp.generated.resources.icon3
import kmp_bottom_bar.composeapp.generated.resources.icon4
import kmp_bottom_bar.composeapp.generated.resources.icon5
import kmp_bottom_bar.composeapp.generated.resources.icon6
import kmp_bottom_bar.composeapp.generated.resources.icon7
import kmp_bottom_bar.composeapp.generated.resources.icon8
import kmp_bottom_bar.composeapp.generated.resources.icon9
import org.mejdi14.core.bottombar.data.BottomBarIcon
import org.mejdi14.tinyGlide.bottombar.TinyGlideDefaultParentContent
import org.mejdi14.tinyGlide.bottombar.TinyGlideBottomBar
import org.mejdi14.tinyGlide.data.TinyGlideAnimationConfig
import org.mejdi14.tinyGlide.data.TinyGlideChildrenLayout
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideItemDecoration
import org.mejdi14.tinyGlide.data.rememberTinyGlideState
import org.mejdi14.tinyGlide.enum.AnimationType
import org.mejdi14.tinyGlide.enum.TinyGlideChildrenPlacement
import org.mejdi14.tinyGlide.enum.TinyGlideOrientation
import org.mejdi14.tinyGlide.enum.TinyGlideVerticalSide
import org.mejdi14.tinyGlide.listeners.TinyGlideActionListener
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun TinyGlideDemo(modifier: Modifier = Modifier) {
    var itemSize by remember { mutableStateOf(54f) }
    var itemSpacing by remember { mutableStateOf(10f) }
    var childSpacing by remember { mutableStateOf(8f) }
    var parentHoverScale by remember { mutableStateOf(1.2f) }
    var selectedScale by remember { mutableStateOf(1.2f) }
    var childHoverScale by remember { mutableStateOf(1.1f) }
    var animationDuration by remember { mutableStateOf(300f) }
    var childAnimation by remember { mutableStateOf(AnimationType.SCALE) }
    var decorations by remember { mutableStateOf("Off") }
    var itemCount by remember { mutableStateOf(10) }
    var palette by remember { mutableStateOf("Storybook") }
    var orientation by remember { mutableStateOf(TinyGlideOrientation.HORIZONTAL) }
    var childrenPlacement by remember { mutableStateOf(TinyGlideChildrenPlacement.AUTO) }
    var edgePadding by remember { mutableStateOf(5f) }
    var verticalSide by remember { mutableStateOf(TinyGlideVerticalSide.END) }
    var contentStyle by remember { mutableStateOf("Default") }
    var showcaseRows by remember { mutableStateOf(3) }
    var itemsPerLine by remember { mutableStateOf(3) }
    var customItem by remember { mutableStateOf("Slider") }
    var customItemValue by remember { mutableStateOf(0.42f) }
    val tinyGlideState = rememberTinyGlideState()
    val backgroundInteractionSource = remember { MutableInteractionSource() }
    val scope = rememberCoroutineScope()
    val layoutDirection = LocalLayoutDirection.current

    val items = remember(
        itemSize,
        itemSpacing,
        childSpacing,
        parentHoverScale,
        selectedScale,
        childHoverScale,
        animationDuration,
        childAnimation,
        decorations,
        itemCount,
        palette,
        showcaseRows,
        itemsPerLine,
    ) {
        tinyGlideDemoItems(
            itemSize = itemSize.dp,
            itemSpacing = itemSpacing.dp,
            childSpacing = childSpacing.dp,
            parentHoverScale = parentHoverScale,
            selectedScale = selectedScale,
            childHoverScale = childHoverScale,
            animationDurationMillis = animationDuration.toInt(),
            childAnimation = childAnimation,
            decorations = decorations,
            storybookPalette = palette == "Storybook",
            showcaseRows = showcaseRows,
            itemsPerLine = itemsPerLine,
        ).take(itemCount)
    }
    Box(
        modifier
            .fillMaxSize()
            .clickable(
                interactionSource = backgroundInteractionSource,
                indication = null,
                onClick = tinyGlideState::dismiss,
            ),
    ) {
        PlaygroundPanel(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    start = if (
                        orientation == TinyGlideOrientation.VERTICAL &&
                            verticalSide == TinyGlideVerticalSide.START
                    ) 96.dp else 0.dp,
                    end = if (
                        orientation == TinyGlideOrientation.VERTICAL &&
                            verticalSide == TinyGlideVerticalSide.END
                    ) 96.dp else 0.dp,
                    bottom = if (orientation == TinyGlideOrientation.HORIZONTAL) 112.dp else 0.dp,
                ),
        ) {
            PlaygroundOptions(
                label = "Orientation",
                options = listOf("Horizontal", "Vertical"),
                selected = when (orientation) {
                    TinyGlideOrientation.HORIZONTAL -> "Horizontal"
                    TinyGlideOrientation.VERTICAL -> "Vertical"
                },
                onSelected = { selectedOrientation ->
                    tinyGlideState.dismiss()
                    orientation = when (selectedOrientation) {
                        "Vertical" -> TinyGlideOrientation.VERTICAL
                        else -> TinyGlideOrientation.HORIZONTAL
                    }
                },
            )
            if (orientation == TinyGlideOrientation.VERTICAL) {
                PlaygroundOptions(
                    label = "Vertical side",
                    options = listOf("Start", "End"),
                    selected = verticalSide.name.lowercase().replaceFirstChar { it.uppercase() },
                    onSelected = {
                        verticalSide = TinyGlideVerticalSide.valueOf(it.uppercase())
                    },
                )
            }
            PlaygroundOptions(
                label = "Children placement",
                options = listOf("Auto", "Above", "Below", "Start", "End"),
                selected = childrenPlacement.name.lowercase().replaceFirstChar { it.uppercase() },
                onSelected = { selectedPlacement ->
                    childrenPlacement = TinyGlideChildrenPlacement.valueOf(
                        selectedPlacement.uppercase(),
                    )
                },
            )
            PlaygroundSlider(
                label = "Edge padding",
                value = edgePadding,
                valueRange = 0f..24f,
                onValueChange = { edgePadding = it },
            )
            PlaygroundSlider(
                label = "Item size",
                value = itemSize,
                valueRange = 44f..64f,
                onValueChange = { itemSize = it },
            )
            PlaygroundSlider(
                label = "Item spacing",
                value = itemSpacing,
                valueRange = 4f..18f,
                onValueChange = { itemSpacing = it },
            )
            PlaygroundSlider(
                label = "Child spacing",
                value = childSpacing,
                valueRange = 4f..16f,
                onValueChange = { childSpacing = it },
            )
            PlaygroundSlider(
                label = "Parent hover scale",
                value = parentHoverScale,
                valueRange = 1f..1.5f,
                onValueChange = { parentHoverScale = it },
                valueText = "${(parentHoverScale * 100).toInt()}%",
            )
            PlaygroundSlider(
                label = "Selected scale",
                value = selectedScale,
                valueRange = 1f..1.5f,
                onValueChange = { selectedScale = it },
                valueText = "${(selectedScale * 100).toInt()}%",
            )
            PlaygroundSlider(
                label = "Child hover scale",
                value = childHoverScale,
                valueRange = 1f..1.5f,
                onValueChange = { childHoverScale = it },
                valueText = "${(childHoverScale * 100).toInt()}%",
            )
            PlaygroundSlider(
                label = "Animation duration",
                value = animationDuration,
                valueRange = 100f..700f,
                onValueChange = { animationDuration = it },
                valueText = "${animationDuration.toInt()} ms",
            )
            PlaygroundOptions(
                label = "Children animation",
                options = listOf("Scale", "Fade", "Slide"),
                selected = childAnimation.name.lowercase().replaceFirstChar { it.uppercase() },
                onSelected = { childAnimation = AnimationType.valueOf(it.uppercase()) },
            )
            PlaygroundOptions(
                label = "Showcase rows",
                options = listOf("1", "2", "3"),
                selected = showcaseRows.toString(),
                onSelected = { showcaseRows = it.toInt() },
            )
            PlaygroundOptions(
                label = "Items per row",
                options = listOf("2", "3", "4"),
                selected = itemsPerLine.toString(),
                onSelected = { itemsPerLine = it.toInt() },
            )
            PlaygroundOptions(
                label = "Custom item",
                options = listOf("Slider", "Off"),
                selected = customItem,
                onSelected = { customItem = it },
            )
            PlaygroundOptions(
                label = "Decorations",
                options = listOf("Off", "Labels", "Badges", "Tooltips", "All"),
                selected = decorations,
                onSelected = { decorations = it },
            )
            PlaygroundOptions(
                label = "Items",
                options = listOf("6", "10", "13"),
                selected = itemCount.toString(),
                onSelected = { itemCount = it.toInt() },
            )
            PlaygroundOptions(
                label = "Palette",
                options = listOf("Storybook", "Minimal"),
                selected = palette,
                onSelected = { palette = it },
            )
            PlaygroundOptions(
                label = "Content slot",
                options = listOf("Default", "Numbered"),
                selected = contentStyle,
                onSelected = { contentStyle = it },
            )
            PlaygroundOptions(
                label = "State actions",
                options = listOf("Clear", "Select 1", "Expand 3", "Scroll last"),
                selected = "",
                onSelected = { action ->
                    when (action) {
                        "Clear" -> tinyGlideState.dismiss()
                        "Select 1" -> tinyGlideState.select(0)
                        "Expand 3" -> tinyGlideState.expand(2)
                        "Scroll last" -> scope.launch {
                            tinyGlideState.scrollTo(items.lastIndex)
                        }
                    }
                },
            )
        }

        TinyGlideBottomBar(
            bottomBarItems = items,
            parentModifier = Modifier.align(
                when (orientation) {
                    TinyGlideOrientation.HORIZONTAL -> Alignment.BottomCenter
                    TinyGlideOrientation.VERTICAL -> when (verticalSide) {
                        TinyGlideVerticalSide.START -> Alignment.CenterStart
                        TinyGlideVerticalSide.END -> Alignment.CenterEnd
                    }
                },
            ),
            tinyGlideActionListener = object : TinyGlideActionListener {
                override fun onClick(item: TinyGlideItem, index: Int?) = Unit

                override fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>) = Unit
            },
            state = tinyGlideState,
            orientation = orientation,
            verticalSide = verticalSide,
            childrenPlacement = childrenPlacement,
            edgePadding = edgePadding.dp,
            childrenLayout = TinyGlideChildrenLayout(
                itemsPerLine = itemsPerLine,
                lineSpacing = childSpacing.dp,
                customContentSize = DpSize(
                    width = (itemSize * 0.78f + childSpacing * 2f).dp * itemsPerLine,
                    height = 50.dp,
                ),
                customContentSpacing = childSpacing.dp,
            ),
            customChildrenContent = if (customItem == "Slider") {
                { _, _ ->
                    StorybookSlider(
                        value = customItemValue,
                        onValueChange = { customItemValue = it },
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            } else {
                null
            },
            showCustomChildrenContent = { item -> item.key == "parent-4" },
            parentContent = { item, position, visualState ->
                TinyGlideDefaultParentContent(item, visualState)
                if (contentStyle == "Numbered") {
                    Text(
                        text = "${position.parentIndex + 1}",
                        color = Color.White,
                        fontSize = 9.sp,
                        modifier = Modifier
                            .align(
                                if (layoutDirection == LayoutDirection.Ltr) {
                                    Alignment.BottomEnd
                                } else {
                                    Alignment.BottomStart
                                },
                            )
                            .background(Color(0xCC27272A), CircleShape)
                            .padding(horizontal = 5.dp, vertical = 2.dp),
                    )
                }
            },
        )
    }
}

@Composable
private fun StorybookSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val updateValue = { position: Float, width: Float ->
        val trackInset = with(density) { 15.dp.toPx() }
        onValueChange(
            ((position - trackInset) / (width - trackInset * 2f)).coerceIn(0f, 1f),
        )
    }
    Canvas(
        modifier = modifier
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(value, 0f..1f)
                setProgress { targetValue ->
                    onValueChange(targetValue.coerceIn(0f, 1f))
                    true
                }
            }
            .pointerInput(onValueChange) {
                detectTapGestures { offset ->
                    updateValue(offset.x, size.width.toFloat())
                }
            }
            .pointerInput(onValueChange) {
                detectDragGestures(
                    onDragStart = { offset ->
                        updateValue(offset.x, size.width.toFloat())
                    },
                    onDrag = { change, _ ->
                        change.consume()
                        updateValue(change.position.x, size.width.toFloat())
                    },
                )
            },
    ) {
        val frameColor = Color(0xFFA86461)
        val ornamentColor = Color(0xFF9B625F)
        val shadowColor = Color(0xFF756474)
        val backgroundColor = Color(0xFFEA967F)
        val trackColor = Color(0xFFFFD2BB)
        val thumbColor = Color(0xFF514F4B)
        val corner = 6.dp.toPx()
        val borderWidth = 1.25.dp.toPx()
        val shadowOffset = 2.dp.toPx()
        val inset = 1.dp.toPx()
        val trackInset = 15.dp.toPx()
        val trackHeight = 5.dp.toPx()
        val trackTop = (size.height - trackHeight) / 2f
        val trackWidth = size.width - trackInset * 2f
        val thumbWidth = 17.dp.toPx()
        val thumbHeight = 19.dp.toPx()
        val thumbCenter = trackInset + trackWidth * value.coerceIn(0f, 1f)

        drawRoundRect(
            color = shadowColor.copy(alpha = 0.8f),
            topLeft = androidx.compose.ui.geometry.Offset(0f, shadowOffset),
            size = androidx.compose.ui.geometry.Size(size.width, size.height - shadowOffset),
            cornerRadius = CornerRadius(corner, corner),
        )
        drawRoundRect(
            color = backgroundColor,
            size = size,
            cornerRadius = CornerRadius(corner, corner),
        )
        drawRoundRect(
            color = frameColor,
            topLeft = androidx.compose.ui.geometry.Offset(inset, inset),
            size = androidx.compose.ui.geometry.Size(
                size.width - inset * 2f,
                size.height - inset * 2f,
            ),
            cornerRadius = CornerRadius(corner - inset, corner - inset),
            style = Stroke(borderWidth),
        )
        drawRoundRect(
            color = trackColor,
            topLeft = androidx.compose.ui.geometry.Offset(trackInset, trackTop),
            size = androidx.compose.ui.geometry.Size(trackWidth, trackHeight),
            cornerRadius = CornerRadius(1.5.dp.toPx(), 1.5.dp.toPx()),
        )
        drawRoundRect(
            color = thumbColor,
            topLeft = androidx.compose.ui.geometry.Offset(
                thumbCenter - thumbWidth / 2f,
                (size.height - thumbHeight) / 2f,
            ),
            size = androidx.compose.ui.geometry.Size(thumbWidth, thumbHeight),
            cornerRadius = CornerRadius(0.75.dp.toPx(), 0.75.dp.toPx()),
        )

        val leftCurl = Path().apply {
            moveTo(8.dp.toPx(), size.height - 5.dp.toPx())
            cubicTo(
                3.dp.toPx(),
                size.height - 3.dp.toPx(),
                3.dp.toPx(),
                size.height - 10.dp.toPx(),
                8.dp.toPx(),
                size.height - 10.dp.toPx(),
            )
        }
        val rightCurl = Path().apply {
            moveTo(size.width - 9.dp.toPx(), 5.dp.toPx())
            cubicTo(
                size.width - 3.dp.toPx(),
                2.dp.toPx(),
                size.width - 3.dp.toPx(),
                10.dp.toPx(),
                size.width - 8.dp.toPx(),
                11.dp.toPx(),
            )
        }
        drawPath(leftCurl, ornamentColor, style = Stroke(0.8.dp.toPx()))
        drawPath(rightCurl, ornamentColor, style = Stroke(0.8.dp.toPx()))
        drawLine(
            ornamentColor,
            androidx.compose.ui.geometry.Offset(size.width / 2f - 2.dp.toPx(), 3.dp.toPx()),
            androidx.compose.ui.geometry.Offset(size.width / 2f - 3.dp.toPx(), 7.dp.toPx()),
            0.7.dp.toPx(),
        )
        drawLine(
            ornamentColor,
            androidx.compose.ui.geometry.Offset(size.width / 2f + 1.dp.toPx(), 3.dp.toPx()),
            androidx.compose.ui.geometry.Offset(size.width / 2f, 7.dp.toPx()),
            0.7.dp.toPx(),
        )
        drawLine(
            ornamentColor,
            androidx.compose.ui.geometry.Offset(size.width - 10.dp.toPx(), size.height - 7.dp.toPx()),
            androidx.compose.ui.geometry.Offset(size.width - 7.dp.toPx(), size.height - 10.dp.toPx()),
            0.7.dp.toPx(),
        )
        drawLine(
            ornamentColor,
            androidx.compose.ui.geometry.Offset(size.width - 7.dp.toPx(), size.height - 6.dp.toPx()),
            androidx.compose.ui.geometry.Offset(size.width - 5.dp.toPx(), size.height - 8.dp.toPx()),
            0.7.dp.toPx(),
        )
    }
}

private fun tinyGlideDemoItems(
    itemSize: Dp,
    itemSpacing: Dp,
    childSpacing: Dp,
    parentHoverScale: Float,
    selectedScale: Float,
    childHoverScale: Float,
    animationDurationMillis: Int,
    childAnimation: AnimationType,
    decorations: String,
    storybookPalette: Boolean,
    showcaseRows: Int,
    itemsPerLine: Int,
): List<TinyGlideItem> {
    val illustrations = listOf(
        Res.drawable.icon1,
        Res.drawable.icon2,
        Res.drawable.icon3,
        Res.drawable.icon4,
        Res.drawable.icon5,
        Res.drawable.icon6,
        Res.drawable.icon7,
        Res.drawable.icon8,
        Res.drawable.icon9,
        Res.drawable.icon10,
        Res.drawable.icon11,
        Res.drawable.icon12,
        Res.drawable.icon13,
    )
    val storybookColors = listOf(
        Color(0xFF756474),
        Color(0xFFED8A67),
        Color(0xFFF2A078),
        Color(0xFFF1C66C),
        Color(0xFFE5D6B8),
        Color(0xFFA8B493),
        Color(0xFFBDC47F),
        Color(0xFF91A97F),
        Color(0xFF9AB5B8),
        Color(0xFF8D9392),
        Color(0xFFB8C9CF),
        Color(0xFFEAA082),
        Color(0xFFC9B28F),
    )
    val parentColors = if (storybookPalette) {
        storybookColors
    } else {
        List(illustrations.size) { Color(0xFF27272A) }
    }
    val labels = listOf(
        "Castle",
        "Cottage",
        "Craft",
        "Bridge",
        "Canvas",
        "Garden",
        "Bloom",
        "Forest",
        "Rain",
        "Camera",
        "Moon",
        "Trail",
        "Map",
    )

    return illustrations.mapIndexed { parentIndex, resource ->
        val childCount = when {
            parentIndex == 4 -> showcaseRows * itemsPerLine
            parentIndex % 5 == 0 -> itemsPerLine * 2
            parentIndex % 3 == 0 -> itemsPerLine
            parentIndex % 3 == 1 -> 2
            else -> 3
        }
        val children = List(childCount) { childIndex ->
            val illustrationIndex = (parentIndex + childIndex + 1) % illustrations.size
            tinyGlideItem(
                key = "parent-$parentIndex-child-$childIndex",
                icon = illustratedIcon(
                    resource = illustrations[illustrationIndex],
                    description = "Child action ${childIndex + 1}",
                ),
                size = itemSize * 0.78f,
                spacing = childSpacing,
                backgroundColor = parentColors[illustrationIndex],
                animation = TinyGlideAnimationConfig(
                    childHoverScale = childHoverScale,
                    childHoverDurationMillis = animationDurationMillis,
                ),
                decoration = TinyGlideItemDecoration(
                    tooltip = if (decorations == "Tooltips" || decorations == "All") {
                        "Open child ${childIndex + 1}"
                    } else {
                        null
                    },
                ),
            )
        }
        tinyGlideItem(
            key = "parent-$parentIndex",
            icon = illustratedIcon(resource, "Story item ${parentIndex + 1}"),
            size = itemSize,
            spacing = itemSpacing,
            backgroundColor = parentColors[parentIndex],
            subItems = children,
            animation = TinyGlideAnimationConfig(
                parentHoverScale = parentHoverScale,
                parentSelectedScale = selectedScale,
                childHoverScale = childHoverScale,
                parentHoverDurationMillis = animationDurationMillis,
                parentSelectionDurationMillis = animationDurationMillis,
                childHoverDurationMillis = animationDurationMillis,
                childAppearanceDurationMillis = animationDurationMillis,
                childDisappearanceDurationMillis = animationDurationMillis,
                childAppearanceAnimation = childAnimation,
            ),
            decoration = TinyGlideItemDecoration(
                label = if (decorations == "Labels" || decorations == "All") {
                    labels[parentIndex]
                } else {
                    null
                },
                badge = if (
                    (decorations == "Badges" || decorations == "All") && parentIndex % 3 == 0
                ) {
                    (parentIndex + 1).toString()
                } else {
                    null
                },
                tooltip = if (decorations == "Tooltips" || decorations == "All") {
                    labels[parentIndex]
                } else {
                    null
                },
            ),
        )
    }
}

private fun illustratedIcon(
    resource: DrawableResource,
    description: String,
): BottomBarIcon = BottomBarIcon(
    resource = resource,
    tint = Color.Unspecified,
    selectedTint = Color.Unspecified,
    contentDescription = description,
    sizeReduction = 8.dp,
)

private fun tinyGlideItem(
    key: String,
    icon: BottomBarIcon,
    size: Dp,
    spacing: Dp,
    backgroundColor: Color,
    subItems: List<TinyGlideItem> = emptyList(),
    animation: TinyGlideAnimationConfig,
    decoration: TinyGlideItemDecoration,
): TinyGlideItem = TinyGlideItem(
    key = key,
    icon = icon,
    size = size,
    shape = RoundedCornerShape(8.dp),
    itemSeparationSpace = spacing,
    parentAndSubVerticalSeparationSpace = 12.dp,
    hoverCancelDurationMillis = 220,
    backgroundColor = backgroundColor,
    selectedBackgroundColor = backgroundColor,
    subTinyGlideItems = subItems,
    animation = animation,
    decoration = decoration,
)
