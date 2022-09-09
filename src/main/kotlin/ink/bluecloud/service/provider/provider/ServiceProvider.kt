package ink.bluecloud.service.provider.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.service.ClientService
import ink.bluecloud.ink.bluecloud.service.provider.ServiceResources
import ink.bluecloud.ink.bluecloud.service.provider.ServiceType
import ink.bluecloud.model.networkapi.NetWorkApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

abstract class ServiceProvider: ServiceResources {
    protected val serviceMap = HashMap<KClass<out ClientService>, ClientService>()

    /**
     * 全局资源列表
     * */
    protected var injectArgs = HashMap<String,Any>()
    /**
     * 服务内部资源列表
     * */
    protected var localInjectArgs = HashMap<String, Any>()

    //网络访问资源
    override lateinit var httpClient: HttpClient
    override lateinit var netWorkApiProvider: NetWorkApiProvider

    //协程控制资源
    override lateinit var ioScope: CoroutineScope
    override lateinit var uiScope: CoroutineScope


    override lateinit var injectTypes: Map<String, ServiceType>

    abstract fun <T: ClientService> isService(service: KClass<T>):Boolean
}