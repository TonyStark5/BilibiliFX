package ink.bluecloud.ui.homeview

import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.service.clientservice.push.PushServiceProviderImpl
import ink.bluecloud.service.clientservice.push.homepush.HomeViewPushService
import ink.bluecloud.service.clientservice.release.ReleaseServiceProvider
import ink.bluecloud.service.clientservice.release.releaseOkhttp.ReleaseService
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import ink.bluecloud.ui.homeview.fragment.pushdisplaycard.PushDisplayCard
import javafx.application.Platform
import kotlinx.coroutines.launch
import tornadofx.*

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