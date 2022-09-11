package ink.bluecloud.ui.homeview

import javafx.scene.layout.FlowPane
import tornadofx.*

abstract class HomeViewNodes: View("Bilibili，干杯~") {
    var pushPane by  singleAssign<FlowPane>()
}