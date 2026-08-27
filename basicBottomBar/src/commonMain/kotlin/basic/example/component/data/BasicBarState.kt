package basic.mejdi14.component.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class BasicBarState(initialSelectedIndex: Int? = 0) {
    var selectedIndex: Int? by mutableStateOf(initialSelectedIndex)
        private set

    init {
        require(initialSelectedIndex == null || initialSelectedIndex >= 0) {
            "initialSelectedIndex cannot be negative"
        }
    }

    fun select(index: Int?) {
        require(index == null || index >= 0) { "index cannot be negative" }
        selectedIndex = index
    }
}

@Composable
fun rememberBasicBarState(initialSelectedIndex: Int? = 0): BasicBarState =
    remember { BasicBarState(initialSelectedIndex) }
