package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmp_bottom_bar.composeapp.generated.resources.Res
import kmp_bottom_bar.composeapp.generated.resources.home_line
import kmp_bottom_bar.composeapp.generated.resources.menu_meatballs
import kmp_bottom_bar.composeapp.generated.resources.open_reader
import kmp_bottom_bar.composeapp.generated.resources.papers
import org.example.core.bottombar.BottomBarIdentifier
import org.example.tinyGlide.bottombar.TinyGlideBottomBar
import org.example.tinyGlide.data.TinyGlideItem
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
      Box(Modifier.fillMaxSize()){
          val bottomBarItems =
              listOf(
                  TinyGlideItem(BottomBarIdentifier.Home, Res.drawable.home_line, "Home", subTinyGlideItems = listOf(
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                  )
                  ),
                  TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers", subTinyGlideItems = listOf(
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                  )),
                  TinyGlideItem(BottomBarIdentifier.Mosque, Res.drawable.open_reader, "Mosque", subTinyGlideItems = listOf(
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                  )),
                  TinyGlideItem(BottomBarIdentifier.Menu, Res.drawable.menu_meatballs, "Menu", subTinyGlideItems = listOf(
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                      TinyGlideItem(BottomBarIdentifier.Papers, Res.drawable.papers, "Papers"),
                  )),
              )
          TinyGlideBottomBar(bottomBarItems, Modifier.align(Alignment.BottomCenter)){

          }
      }
    }
}