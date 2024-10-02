package org.example.tinyGlide.listeners

import org.example.core.bottombar.listener.GlobalClickActionListener
import org.example.tinyGlide.data.TinyGlideItem

interface TinyGlideActionListener : GlobalClickActionListener<TinyGlideItem> {
    fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>)
}