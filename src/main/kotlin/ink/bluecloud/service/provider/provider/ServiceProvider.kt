package ink.bluecloud.ink.bluecloud.service.provider.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProvider
import ink.bluecloud.ink.bluecloud.service.provider.ClientService
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

abstract class ServiceProvider {
    protected val serviceMap = HashMap<KClass<out ClientService>, ClientService>()
    protected var injectArgs = HashMap<String,Any>()

    protected lateinit var httpClient: HttpClient
    protected lateinit var netWorkApiProvider: NetWorkApiProvider

    protected lateinit var ioScope:CoroutineScope
    protected lateinit var uiScope:CoroutineScope

    abstract fun <T: ClientService> isService(service: KClass<T>):Boolean
}