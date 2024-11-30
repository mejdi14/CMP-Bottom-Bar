package basic.example.component.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.data.BottomBarIcon
import org.example.core.bottombar.data.BottomBarItem
import org.example.core.bottombar.data.BottomBarTitle
import org.example.core.bottombar.listener.ClickActionListener
import org.example.core.bottombar.listener.EmptyClickActionListener
import org.example.core.bottombar.listener.HoverActionListener
import org.example.core.bottombar.listener.emptyHoverActionListener

data class BasicItem(
    override val icon: BottomBarIcon,
    override val contentDescription: String = "bottom bar icon",
    override val size: Dp = 50.dp,
    override val backgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = backgroundColor,
    override val itemShape: Shape = RoundedCornerShape(10.dp),
    override var index: Int = -1,
    override val disableClickIfAlreadySelected: Boolean = true,
    val radius: Dp? = null,
    val onSelectItemSizeChangeFriction: Float? = null,
    val onSelectItemSizeChangeDurationMillis: Int? = null,
    val hoverCancelDurationMillis: Long? = null,
    val itemSeparationSpace: Dp? = null,
    var itemCoordinatesOffset: Offset? = null,
    val parentAndSubVerticalSeparationSpace: Dp? = null,
    val marginForScreenSizeChanges: Float? = null,
    var parentItemDynamicSize: MutableState<Dp> = mutableStateOf(size),
    val hoverText: String = "holder",
    val hoverActionListener: HoverActionListener<BasicItem> = emptyHoverActionListener(),
    val clickActionListener: ClickActionListener = EmptyClickActionListener
) : BottomBarItem() {
    override val withTitleShown: Boolean = false
    override val title: BottomBarTitle = BottomBarTitle("bottomBarTitle")
}