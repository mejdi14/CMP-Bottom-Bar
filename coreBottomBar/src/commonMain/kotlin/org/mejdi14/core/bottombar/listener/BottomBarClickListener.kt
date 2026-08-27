package org.mejdi14.core.bottombar.listener

fun interface BottomBarClickListener<in T> {
    fun onClick(item: T, index: Int?)
}
