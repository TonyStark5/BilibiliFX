package ink.bluecloud.ui.homeview

import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.ink.bluecloud.service.clientservice.release.ReleaseServiceProvider
import ink.bluecloud.service.clientservice.release.releaseOkhttp.ReleaseService
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import javafx.application.Platform
import tornadofx.*

class HomeViewController: Controller() {
    private val dispatcher by inject<ClientServiceDispatcher>()

    val view = find<HomeView>().apply {
        (root.top as TitleBar).onCloseRequest = {
            dispatcher[ReleaseServiceProvider::class].provideService(ReleaseService::class) {
                onExit()
                Platform.exit()
            }
        }
    }
}