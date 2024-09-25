package org.example.aztopia.listeners

import org.example.aztopia.data.AztopiaItem

interface AztopiaActionListener {
    fun onAztopiaItemClickListener(item: AztopiaItem, index: Int)
    fun onBonusItemClickListener(item: AztopiaItem, index: Pair<Int, Int>)
}