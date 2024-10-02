package org.example.core.bottombar.listener



interface HoverActionListener<T> {
    fun onHoverEnter(item: T)
    fun onHoverExit(item: T)
    fun onHoverParentItem(item: T)
    fun onHoverSubItem(item: T)
}