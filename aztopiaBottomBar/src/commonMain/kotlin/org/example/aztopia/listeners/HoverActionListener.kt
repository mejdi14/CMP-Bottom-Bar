package org.example.aztopia.listeners

import org.example.aztopia.data.TinyGlideItem


interface HoverActionListener {
    fun onHoverEnter(tinyGlideItem: TinyGlideItem)
    fun onHoverExit(tinyGlideItem: TinyGlideItem)
    fun onHoverParentItem(tinyGlideItem: TinyGlideItem)
    fun onHoverSubItem(tinyGlideItem: TinyGlideItem)
}