package ink.bluecloud.ui.homeview

import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.ui.homeview.fragment.sliderbar.CloudSlideBar
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import tornadofx.*

class HomeView: HomeViewNodes() {

    override val root = borderpane {
        top = TitleBar("BilibiliFx", primaryStage)

        center = borderpane {
            //                           "首页", "动态", "直播", "分区"
            left = CloudSlideBar(arrayOf("\uE8C6", "\uE8C7", "\uE8C8", "\uE8C4")).apply {
                prefWidth = 50.0
            }

            center = scrollpane {
                pushPane = flowpane {
                    widthProperty().addListener { _, _, newValue ->
                        if (children.size == 0) return@addListener
                        val cardWidth = (children[0] as Pane).width
                        val cardCount = ((newValue.toDouble() - 20.0) / cardWidth).toInt()
                        hgap = if ((newValue.toDouble() + 20) >= ((cardWidth * cardCount) + ((cardCount - 1) * 10))) {
                            (newValue.toDouble() - (cardCount * cardWidth) + ((cardCount - 1) * 10)) / (cardCount + 1)
                        } else {
                            10.0
                        }.run {
                            if (this < 10.0) 10.0 else this
                        }
                    }
                    vgap = 10.0
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
        prefWidth = 1350.0
        prefHeight = 1000.0
    }
}