package org.mejdi14.project.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

private val PanelBackground = Color(0xFFFFFFFF)
private val PanelOutline = Color(0xFFE4E4E7)
private val ChipBackground = Color(0xFFF4F4F5)
private val SelectedChipBackground = Color(0xFF18181B)
private val PrimaryText = Color(0xFF18181B)
private val SecondaryText = Color(0xFF71717A)

@Composable
internal fun PlaygroundPanel(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .widthIn(max = 620.dp)
            .fillMaxWidth(0.9f)
            .heightIn(max = 390.dp)
            .background(PanelBackground, RoundedCornerShape(20.dp))
            .border(1.dp, PanelOutline, RoundedCornerShape(20.dp))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Live controls",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText,
        )
        content()
    }
}

@Composable
internal fun PlaygroundSlider(
    label: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    valueText: String = "${value.roundToInt()} dp",
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(label, style = MaterialTheme.typography.labelLarge, color = PrimaryText)
            Text(valueText, style = MaterialTheme.typography.labelMedium, color = SecondaryText)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
        )
    }
}

@Composable
internal fun PlaygroundOptions(
    label: String,
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge, color = PrimaryText)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            options.forEach { option ->
                val isSelected = option == selected
                Text(
                    text = option,
                    modifier = Modifier
                        .background(
                            color = if (isSelected) SelectedChipBackground else ChipBackground,
                            shape = RoundedCornerShape(10.dp),
                        )
                        .clickable { onSelected(option) }
                        .padding(horizontal = 13.dp, vertical = 9.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isSelected) Color.White else PrimaryText,
                )
            }
        }
    }
}
