package org.example.core.bottombar.listener

interface GlobalClickActionListener<T> {
    fun onItemClickListener(item: T, index: Int)
}