package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.papers
import ui.BottomBarIdentifier
import ui.BottomBarItem
import ui.BottomBarSheet

@Composable
@Preview
fun App() {
    MaterialTheme {
      Box(Modifier.fillMaxSize()){
          val bottomBarItems =
              listOf(
                  BottomBarItem(BottomBarIdentifier.Home, Res.drawable.home_line, "Home"),
                  BottomBarItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                  BottomBarItem(BottomBarIdentifier.Mosque, Res.drawable.open_reader, "Mosque"),
                  BottomBarItem(BottomBarIdentifier.Menu, Res.drawable.menu_meatballs, "Menu"),
              )
          BottomBarSheet(bottomBarItems){

          }
      }
    }
}