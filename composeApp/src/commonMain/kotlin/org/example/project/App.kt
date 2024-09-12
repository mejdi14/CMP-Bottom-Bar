package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import expand.example.expandable.bottombar.ExpandableBottomBar
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.papers
import org.jetbrains.compose.ui.tooling.preview.Preview


import org.example.core.bottombar.BottomBarIdentifier
import org.example.core.bottombar.BottomBarItem
import org.example.tinyGlide.data.TinyGlideItem
import ui.TinyGlideBottomBar


@Composable
@Preview
fun App() {
    MaterialTheme {
      Box(Modifier.fillMaxSize()){
          val bottomBarItems =
              listOf(
                  TinyGlideItem(BottomBarIdentifier.Home, Res.drawable.home_line, "Home"),
                  TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                  TinyGlideItem(BottomBarIdentifier.Mosque, Res.drawable.open_reader, "Mosque"),
                  TinyGlideItem(BottomBarIdentifier.Menu, Res.drawable.menu_meatballs, "Menu"),
              )
          TinyGlideBottomBar(bottomBarItems, Modifier.align(Alignment.BottomCenter)){

          }
      }
    }
}