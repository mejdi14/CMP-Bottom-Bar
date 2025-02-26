package org.mejdi14.tinyGlide.listeners

import org.mejdi14.core.bottombar.listener.GlobalClickActionListener
import org.mejdi14.tinyGlide.data.TinyGlideItem

interface TinyGlideActionListener : GlobalClickActionListener<TinyGlideItem> {
    fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>)
}