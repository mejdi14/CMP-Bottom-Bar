package org.example.aztopia.listeners

import org.example.aztopia.data.TinyGlideItem

interface TinyGlideActionListener {
    fun onTinyGlideItemClickListener(item: TinyGlideItem, index: Int)
    fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>)
}