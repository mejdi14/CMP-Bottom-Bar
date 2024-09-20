package org.example.tinyGlide.listeners

import org.example.tinyGlide.data.TinyGlideItem

interface TinyGlideActionListener {
    fun onTinyGlideItemClickListener(item: TinyGlideItem, index: Int)
    fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>)
}