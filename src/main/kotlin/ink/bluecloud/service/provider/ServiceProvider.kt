package ink.bluecloud.ink.bluecloud.service.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlin.reflect.KClass

abstract class ServiceProvider(
    httpClient: HttpClient,
    netWorkApiProvider: NetWorkApiProvider
) {
    protected val ioScope = CoroutineScope(Dispatchers.IO)
    protected val uiScope = CoroutineScope(Dispatchers.JavaFx)

    protected val serviceMap = HashMap<KClass<out ClientService>,ClientService>()

    protected val injectArgs = hashMapOf<String,Any>(
        "httpClient" to httpClient,
        "netWorkApiProvider" to netWorkApiProvider,
        "ioScope" to ioScope,
        "uiScope" to uiScope,
    )

    abstract fun <T: ClientService> isService(service: KClass<T>):Boolean
}