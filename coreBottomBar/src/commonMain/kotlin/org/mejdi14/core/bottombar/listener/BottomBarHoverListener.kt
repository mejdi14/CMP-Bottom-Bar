package org.mejdi14.core.bottombar.listener

fun interface BottomBarHoverListener<in T> {
    fun onHover(item: T, isHovered: Boolean)
}
