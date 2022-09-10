package ink.bluecloud.ui.homeview

import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import tornadofx.*

class HomeViewController: Controller() {
    private val dispatcher by inject<ClientServiceDispatcher>()

    val view = find<HomeView>().apply {

    }
}