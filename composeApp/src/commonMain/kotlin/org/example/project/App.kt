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
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.BottomBarIdentifier
import org.example.project.items.homeItem
import org.example.tinyGlide.bottombar.TinyGlideBottomBar
import org.example.tinyGlide.data.TinyGlideItem
import org.example.tinyGlide.listeners.TinyGlideActionListener
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
      Box(Modifier.fillMaxSize()){
          val bottomBarItems =
              listOf(
                  homeItem,
                  TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers", subTinyGlideItems = listOf(
                      TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers"),
                      TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers"),
                      TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers"),
                      TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers"),
                  )),
                  TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Mosque", subTinyGlideItems = listOf(
                      TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers"),
                  )),
                  TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Menu", subTinyGlideItems = listOf(
                      TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers"),
                      TinyGlideItem( BottomBarIcon(Res.drawable.home_line), "Papers"),
                  )),
              )
          TinyGlideBottomBar(bottomBarItems, Modifier.align(Alignment.BottomCenter), tinyGlideActionListener = object : TinyGlideActionListener{
              override fun onTinyGlideItemClickListener(item: TinyGlideItem, index: Int) {
                  TODO("Not yet implemented")
              }

              override fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>) {
                  TODO("Not yet implemented")
              }

          })
      }
    }
}