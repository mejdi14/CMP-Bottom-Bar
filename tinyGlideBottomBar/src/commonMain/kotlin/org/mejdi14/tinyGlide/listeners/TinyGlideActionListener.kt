package org.mejdi14.tinyGlide.listeners

import org.mejdi14.core.bottombar.listener.BottomBarClickListener
import org.mejdi14.tinyGlide.data.TinyGlideItem
import org.mejdi14.tinyGlide.data.TinyGlideItemPosition

interface TinyGlideActionListener : BottomBarClickListener<TinyGlideItem> {
    fun onSubItemClickListener(item: TinyGlideItem, index: Pair<Int, Int>)

    fun onSelect(item: TinyGlideItem, index: Int) = Unit

    fun onDeselect(item: TinyGlideItem, index: Int) = Unit

    fun onHover(item: TinyGlideItem, position: TinyGlideItemPosition) = Unit

    fun onHoverExit(item: TinyGlideItem, position: TinyGlideItemPosition) = Unit

    fun onFocus(item: TinyGlideItem, position: TinyGlideItemPosition) = Unit

    fun onFocusExit(item: TinyGlideItem, position: TinyGlideItemPosition) = Unit

    fun onExpand(item: TinyGlideItem, index: Int) = Unit

    fun onCollapse(item: TinyGlideItem, index: Int) = Unit

    fun onDismiss(selectedItem: TinyGlideItem?, selectedIndex: Int?) = Unit
}
