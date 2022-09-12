package ink.bluecloud.ui.homeview

import ink.bluecloud.cloudtools.CLOUD_INTERPOLATOR
import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.service.clientservice.push.PushServiceProviderImpl
import ink.bluecloud.service.clientservice.push.homepush.HomeViewPushService
import ink.bluecloud.service.clientservice.release.ReleaseServiceProvider
import ink.bluecloud.service.clientservice.release.releaseOkhttp.ReleaseService
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import ink.bluecloud.ui.homeview.fragment.pushdisplaycard.PushDisplayCard
import ink.bluecloud.ui.timeline
import javafx.application.Platform
import javafx.scene.layout.Pane
import javafx.util.Duration
import kotlinx.coroutines.launch
import tornadofx.*
import kotlin.math.absoluteValue

class HomeViewController: Controller() {
    private val dispatcher by inject<ClientServiceDispatcher>()

    val view = find<HomeView>().apply {
        (root.top as TitleBar).onCloseRequest = {
            dispatcher.service<ReleaseServiceProvider,ReleaseService> {
                onExit()
                Platform.exit()
            }
        }

        pushPane.apply {
            widthProperty().addListener { _, _, newValue ->
                if (children.size == 0) return@addListener

                val cardWidth = (children[0] as Pane).width
                if (cardWidth == 0.0) return@addListener

                val cardCount = ((newValue.toDouble() - 20.0) / cardWidth).toInt().run {
                    if ((newValue.toDouble() - 20.0) - ((this - 1) * hgap) - (this * cardWidth) < 0) {
                        this - 1
                    } else {
                        this
                    }
                }

//                println("target="+newValue.toDouble() + 20)
//                println("now=" + (cardWidth * cardCount) + ((cardCount - 1) * 10))
//                println("cardCount=" + cardCount)

                if ((newValue.toDouble() + 20) >= ((cardWidth * cardCount) + ((cardCount - 1) * 10))) {
                    (newValue.toDouble() - (cardCount * cardWidth) + ((cardCount - 1) * 10)) / (cardCount + 1)
                } else {
                    10.0
                }.run {
                    if ((this - hgap).absoluteValue < 15.0) {
                        hgap = this
                    } else timeline {
                        keyframe(Duration.millis(150.0)) {
                            keyvalue(hgapProperty(), this@run, CLOUD_INTERPOLATOR)
                        }
                    }
                }
            }

            dispatcher.service<PushServiceProviderImpl,HomeViewPushService> {
                uiScope.launch {
                    repeat(50) {
                        children += PushDisplayCard(getCard())
                    }
                }
            }
        }
    }
}