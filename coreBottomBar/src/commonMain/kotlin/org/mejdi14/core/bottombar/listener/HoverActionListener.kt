package org.mejdi14.core.bottombar.listener



interface HoverActionListener<T> {
    fun onHoverEnter(item: T)
    fun onHoverExit(item: T)
    fun onHoverParentItem(item: T)
    fun onHoverSubItem(item: T)
}