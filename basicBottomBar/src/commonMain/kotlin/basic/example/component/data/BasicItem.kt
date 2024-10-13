package basic.example.component.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.core.bottombar.BottomBarIcon
import org.example.core.bottombar.BottomBarItem
import org.example.core.bottombar.BottomBarTitle
import org.example.core.bottombar.listener.ClickActionListener
import org.example.core.bottombar.listener.EmptyClickActionListener
import org.example.core.bottombar.listener.HoverActionListener
import org.example.core.bottombar.listener.emptyHoverActionListener

data class BasicItem(
    override val icon: BottomBarIcon,
    override val contentDescription: String,
    override val size: Dp = 50.dp,
    override val backgroundColor: Color = Color.Blue,
    override val selectedBackgroundColor: Color = backgroundColor,
    override val itemShape: Shape = RoundedCornerShape(10.dp),
    override var index: Int = -1,
    override val disableClickIfAlreadySelected: Boolean = true,
    val radius: Dp = 10.dp,
    val onSelectItemSizeChangeFriction: Float = 1.3f,
    val onSelectItemSizeChangeDurationMillis: Int = 300,
    val hoverCancelDurationMillis: Long = 8,
    val itemSeparationSpace: Dp = 10.dp,
    var itemCoordinatesOffset: Offset? = null,
    val parentAndSubVerticalSeparationSpace: Dp = 10.dp,
    val marginForScreenSizeChanges: Float = 10f,
    var parentItemDynamicSize: MutableState<Dp> = mutableStateOf(size),
    val hoverActionListener: HoverActionListener<BasicItem> = emptyHoverActionListener(),
    val clickActionListener: ClickActionListener = EmptyClickActionListener
) : BottomBarItem() {
    override val withTitleShown: Boolean = false
    override val title: BottomBarTitle = BottomBarTitle("bottomBarTitle")
}