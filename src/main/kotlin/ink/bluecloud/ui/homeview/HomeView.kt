package ink.bluecloud.ui.homeview

import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.service.clientservice.push.PushServiceProviderImpl
import ink.bluecloud.service.clientservice.push.homepush.HomeViewPushService
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import ink.bluecloud.ui.homeview.fragment.pushdisplaycard.PushDisplayCard
import ink.bluecloud.ui.homeview.fragment.sliderbar.CloudSlideBar
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

            center = stackpane {
                flowpane {
                    dispatcher[PushServiceProviderImpl::class].provideService(HomeViewPushService::class) {
                        children += find<PushDisplayCard>(params = mapOf("data" to getCard())).root
                    }
                }
                style {
                    backgroundColor += c(251, 251, 251)
                }
                paddingAll = 10
            }
        }

        style {
            backgroundColor += Color.WHITE
        }
        prefWidth = 1500.0
        prefHeight = 1000.0
    }
}