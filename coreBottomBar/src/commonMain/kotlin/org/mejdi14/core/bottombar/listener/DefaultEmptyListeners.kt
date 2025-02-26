package org.mejdi14.core.bottombar.listener

@Suppress("UNCHECKED_CAST")
fun <T> emptyHoverActionListener(): HoverActionListener<T> = EmptyHoverActionListener as HoverActionListener<T>

val EmptyHoverActionListener = object : HoverActionListener<Any> {
    override fun onHoverEnter(item: Any) {
        // Do nothing
    }

    override fun onHoverExit(item: Any) {
        // Do nothing
    }

    override fun onHoverParentItem(item: Any) {
        // Do nothing
    }

    override fun onHoverSubItem(item: Any) {
        // Do nothing
    }
}


val EmptyClickActionListener =

    object : ClickActionListener {
        override fun onItemClickListener(
        ) {
            // Do nothing
        }
    }