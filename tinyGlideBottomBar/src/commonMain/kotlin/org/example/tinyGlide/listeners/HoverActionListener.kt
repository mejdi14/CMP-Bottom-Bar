package org.example.tinyGlide.listeners

import org.example.tinyGlide.data.TinyGlideItem


interface HoverActionListener {
    fun onHoverEnter(tinyGlideItem: TinyGlideItem)
    fun onHoverExit(tinyGlideItem: TinyGlideItem)
    fun onHoverParentItem(tinyGlideItem: TinyGlideItem)
    fun onHoverSubItem(tinyGlideItem: TinyGlideItem)
}