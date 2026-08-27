package org.mejdi14.tinyGlide.listeners

import org.mejdi14.core.bottombar.listener.BottomBarClickListener
import org.mejdi14.tinyGlide.data.TinyGlideItem

interface TinyGlideActionListener : BottomBarClickListener<TinyGlideItem> {
    fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>)
}
