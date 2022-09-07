package ink.bluecloud.ink.bluecloud.service.provider.dispatcher

import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProviderImpl
import ink.bluecloud.ink.bluecloud.service.net.HttpClientImpl
import ink.bluecloud.ink.bluecloud.service.provider.ClientServiceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import tornadofx.*
import kotlin.reflect.KClass

abstract class ServiceDispatcher: Controller() {
    protected val serviceMap = HashMap<KClass<out ClientServiceProvider>, ClientServiceProvider>()

    protected val httpClient = HttpClientImpl()
    protected val netWorkApiProvider = NetWorkApiProviderImpl()

    protected val ioScope = CoroutineScope(Dispatchers.IO)
    protected val uiScope = CoroutineScope(Dispatchers.JavaFx)

    protected val injectArgs = hashMapOf(
        "httpClient" to httpClient,
        "netWorkApiProvider" to netWorkApiProvider,
        "ioScope" to ioScope,
        "uiScope" to uiScope,
    )

}