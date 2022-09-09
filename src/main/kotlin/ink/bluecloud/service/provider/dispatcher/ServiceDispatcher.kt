package ink.bluecloud.service.provider.dispatcher

import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.service.provider.ServiceResources
import ink.bluecloud.ink.bluecloud.service.provider.ServiceType
import ink.bluecloud.model.networkapi.NetWorkApiProvider
import ink.bluecloud.model.networkapi.NetWorkApiProviderImpl
import ink.bluecloud.service.net.HttpClientImpl
import ink.bluecloud.service.provider.InjectResourcesType
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import tornadofx.*
import kotlin.reflect.KClass

/**
* 本类存放并初始化全局资源
* */
abstract class ServiceDispatcher: Controller(),ServiceResources {
    protected val serviceMap = HashMap<KClass<out ClientServiceProvider>, ClientServiceProvider>()

    @InjectResourcesType(ServiceType.NetWork)
    final override val httpClient: HttpClient = HttpClientImpl()
    @InjectResourcesType(ServiceType.NetWork)
    final override val netWorkApiProvider: NetWorkApiProvider = NetWorkApiProviderImpl()

    final override val ioScope = CoroutineScope(Dispatchers.IO)
    final override val uiScope = CoroutineScope(Dispatchers.JavaFx)

    final override val injectTypes = HashMap<String, ServiceType>()

    /**
    * 最终会向服务提供器注入的参数列表
    * */
    protected val injectArgs = hashMapOf(
        "httpClient" to httpClient,
        "netWorkApiProvider" to netWorkApiProvider,
        "ioScope" to ioScope,
        "uiScope" to uiScope,
        "injectTypes" to injectTypes,
    )

}