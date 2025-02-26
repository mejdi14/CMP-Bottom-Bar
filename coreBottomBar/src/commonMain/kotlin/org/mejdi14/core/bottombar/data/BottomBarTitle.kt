package org.mejdi14.core.bottombar.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

class BottomBarTitle(
    val title: String,
    val selectedTextColor: Color = Color.Unspecified,
    val unselectedTextColor: Color = Color.Unspecified,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val fontStyle: FontStyle? = null,
    val fontWeight: FontWeight? = null,
    val contentDescription: String = "bottom bar text",
    val modifier: Modifier = Modifier,
)