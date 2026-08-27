package basic.mejdi14.component.data

enum class BasicBarPosition {
    HorizontalBottom,
    HorizontalTop,
    VerticalLeft,
    VerticalRight;

    val isHorizontal: Boolean
        get() = this == HorizontalBottom || this == HorizontalTop
}
