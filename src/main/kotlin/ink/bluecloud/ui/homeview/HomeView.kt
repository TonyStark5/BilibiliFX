package ink.bluecloud.ui.homeview

import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.ui.homeview.fragment.sliderbar.CloudSlideBar
import javafx.scene.paint.Color
import tornadofx.*

class HomeView: HomeViewNodes() {

    override val root = borderpane {
        top = TitleBar("BilibiliFx", primaryStage)

        center = borderpane {
            //                           "首页",     "动态",   "直播",    "分区"
            left = CloudSlideBar(arrayOf("\uE8C6", "\uE8C7", "\uE8C8", "\uE8C4")).apply {
                prefWidth = 50.0
            }

            center = scrollpane {
                pushPane = flowpane {
                    vgap = 15.0
                    hgap = 20.0
                }

                style {
                    backgroundColor += c(249, 249, 249)
                    backgroundRadius += box(10.px)
                }

                isFitToWidth = true
                paddingAll = 10
            }

            paddingTop = 10
        }

        style {
            backgroundColor += Color.WHITE
        }
        prefWidth = 1050.0
        prefHeight = 900.0
    }
}