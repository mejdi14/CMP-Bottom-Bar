package org.example.aztopia.listeners

import org.example.aztopia.data.AztopiaItem
import org.example.core.bottombar.listener.GlobalClickActionListener

interface AztopiaActionListener : GlobalClickActionListener<AztopiaItem>{
    fun onAnimatedCircularItemClickListener(index: Int)
}