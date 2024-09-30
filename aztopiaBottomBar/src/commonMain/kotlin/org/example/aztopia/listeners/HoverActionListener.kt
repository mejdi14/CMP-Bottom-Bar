package org.example.aztopia.listeners

import org.example.aztopia.data.AztopiaItem


interface HoverActionListener {
    fun onHoverEnter(aztopiaItem: AztopiaItem)
    fun onHoverExit(aztopiaItem: AztopiaItem)
    fun onHoverParentItem(aztopiaItem: AztopiaItem)
    fun onHoverSubItem(aztopiaItem: AztopiaItem)
}