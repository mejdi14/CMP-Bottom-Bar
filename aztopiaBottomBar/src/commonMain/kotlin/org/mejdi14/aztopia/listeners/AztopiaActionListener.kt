package org.mejdi14.aztopia.listeners

import org.mejdi14.aztopia.data.AztopiaItem
import org.mejdi14.core.bottombar.listener.GlobalClickActionListener

interface AztopiaActionListener : GlobalClickActionListener<AztopiaItem>{
    fun onAnimatedCircularItemClickListener(index: Int)
}