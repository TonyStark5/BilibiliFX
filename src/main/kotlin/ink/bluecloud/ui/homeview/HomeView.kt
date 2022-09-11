package ink.bluecloud.ui.homeview

import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.service.clientservice.push.PushServiceProviderImpl
import ink.bluecloud.service.clientservice.push.homepush.HomeViewPushService
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import ink.bluecloud.ui.homeview.fragment.pushdisplaycard.PushDisplayCard
import ink.bluecloud.ui.homeview.fragment.sliderbar.CloudSlideBar
import javafx.geometry.HPos
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import tornadofx.*

class HomeView: HomeViewNodes() {
    private val dispatcher by inject<ClientServiceDispatcher>()

    override val root = borderpane {
        top = TitleBar("BilibiliFx", primaryStage)

        center = borderpane {
            //                           "首页", "动态", "直播", "分区"
            left = CloudSlideBar(arrayOf("\uE8C6", "\uE8C7", "\uE8C8", "\uE8C4")).apply {
                prefWidth = 50.0
            }

            center = scrollpane {
                pushPane = flowpane {
                    dispatcher[PushServiceProviderImpl::class].provideService(HomeViewPushService::class) {
                       repeat(50) {
                           children += find<PushDisplayCard>(params = mapOf("data" to getCard())).root
                       }
                    }

                    vgap = 10.0
                    hgap = 10.0

                    widthProperty().addListener { _, _, newValue ->
                        val cardWidth = (children[0] as Pane).width
                        val cardCount = ((newValue.toDouble() - 20.0) / cardWidth).toInt()
                        println("newValue=${newValue.toDouble()- 20}")
                        println("cardWidth=$cardWidth")
                        println("cardCount=$cardCount")
                        println("结果=${(cardWidth * cardCount) + ((cardCount - 1) * 10)}")
                        hgap = if ((newValue.toDouble() + 20) >= ((cardWidth * cardCount) + ((cardCount - 1) * 10))) {
                            (newValue.toDouble() - (cardCount * cardWidth) + ((cardCount - 1) * 10)) / (cardCount + 1)
                        } else {
                            10.0
                        }
                    }
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