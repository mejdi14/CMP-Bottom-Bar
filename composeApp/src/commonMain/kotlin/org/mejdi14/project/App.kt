package org.mejdi14.project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mejdi14.project.demo.AztopiaDemo
import org.mejdi14.project.demo.BasicDemo
import org.mejdi14.project.demo.ExpandableDemo
import org.mejdi14.project.demo.FigmaDemo
import org.mejdi14.project.demo.GooeyDemo
import org.mejdi14.project.demo.TinyGlideDemo

@Composable
@Preview
fun App() {
    var destination by remember { mutableStateOf<DemoDestination?>(null) }

    MaterialTheme(colorScheme = DemoColorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppBackground,
        ) {
            val currentDestination = destination
            if (currentDestination == null) {
                DemoCatalog(onOpen = { destination = it })
            } else {
                DemoDetail(
                    destination = currentDestination,
                    onBack = { destination = null },
                )
            }
        }
    }
}

@Composable
private fun DemoCatalog(onOpen: (DemoDestination) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .widthIn(max = 720.dp)
                .padding(horizontal = 24.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    text = "CMP Bottom Bar",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "A collection of interactive bottom-bar styles for Compose Multiplatform.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = SecondaryText,
                )
                Spacer(Modifier.height(20.dp))
            }

            items(DemoDestination.entries, key = DemoDestination::name) { destination ->
                DemoCatalogItem(
                    destination = destination,
                    onClick = { onOpen(destination) },
                )
            }

            item {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "${DemoDestination.entries.size} styles · Android · iOS · Desktop · Web",
                    style = MaterialTheme.typography.labelMedium,
                    color = TertiaryText,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )
            }
        }
    }
}

@Composable
private fun DemoCatalogItem(
    destination: DemoDestination,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(18.dp)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, OutlineColor, shape)
            .clickable(onClick = onClick),
        color = Color.White,
        shape = shape,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(ItemSurface, RoundedCornerShape(13.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = (destination.ordinal + 1).toString().padStart(2, '0'),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText,
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp),
            ) {
                Text(
                    text = destination.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryText,
                )
                Text(
                    text = destination.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = SecondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(ItemSurface, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "›",
                    style = MaterialTheme.typography.titleLarge,
                    color = SecondaryText,
                )
            }
        }
    }
}

@Composable
private fun DemoDetail(
    destination: DemoDestination,
    onBack: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier
                    .size(40.dp)
                    .clickable(onClick = onBack),
                color = ItemSurface,
                shape = CircleShape,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "‹",
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryText,
                    )
                }
            }

            Column(
                modifier = Modifier.padding(start = 14.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = destination.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText,
                )
                Text(
                    text = destination.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = SecondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        HorizontalDivider(color = OutlineColor)

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            Text(
                text = destination.hint,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(28.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = SecondaryText,
            )

            when (destination) {
                DemoDestination.Basic -> BasicDemo(Modifier.fillMaxSize())
                DemoDestination.TinyGlide -> TinyGlideDemo(Modifier.fillMaxSize())
                DemoDestination.Aztopia -> AztopiaDemo(Modifier.fillMaxSize())
                DemoDestination.Expandable -> ExpandableDemo(Modifier.fillMaxSize())
                DemoDestination.Gooey -> GooeyDemo(Modifier.fillMaxSize())
                DemoDestination.Figma -> FigmaDemo(Modifier.fillMaxSize())
            }
        }
    }
}

private enum class DemoDestination(
    val title: String,
    val description: String,
    val hint: String,
) {
    Basic(
        title = "Basic",
        description = "A flexible bar with multiple indicators and orientations.",
        hint = "Tune the bar, then select an item to test it.",
    ),
    TinyGlide(
        title = "Tiny Glide",
        description = "A selectable bottom bar that expands into balanced rows.",
        hint = "Tune the motion, then hover or tap an item.",
    ),
    Aztopia(
        title = "Aztopia",
        description = "A centered action that expands into a circular menu.",
        hint = "Customize the actions, then open the circular menu.",
    ),
    Expandable(
        title = "Expandable",
        description = "A stacked navigation bar with synchronized selection.",
        hint = "Configure the rows, then select an icon.",
    ),
    Gooey(
        title = "Gooey",
        description = "A fluid blurred selection treatment.",
        hint = "Shape the effect, then select a label.",
    ),
    Figma(
        title = "Figma Bottom Bar",
        description = "A compact grouped toolbar inspired by Figma.",
        hint = "Select a tool or mode, then try the light and dark palettes.",
    ),
}

private val DemoColorScheme = lightColorScheme(
    primary = Color(0xFF18181B),
    onPrimary = Color.White,
    background = Color(0xFFF4F4F5),
    onBackground = Color(0xFF18181B),
    surface = Color.White,
    onSurface = Color(0xFF18181B),
)

private val AppBackground = Color(0xFFF4F4F5)
private val ItemSurface = Color(0xFFF4F4F5)
private val OutlineColor = Color(0xFFE4E4E7)
private val PrimaryText = Color(0xFF18181B)
private val SecondaryText = Color(0xFF52525B)
private val TertiaryText = Color(0xFF71717A)
