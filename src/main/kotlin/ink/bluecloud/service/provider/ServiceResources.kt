package ink.bluecloud.ink.bluecloud.service.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.model.networkapi.NetWorkApiProvider
import kotlinx.coroutines.CoroutineScope

interface ServiceResources {
    //网络访问资源
    val httpClient: HttpClient
    val netWorkApiProvider: NetWorkApiProvider

    //协程控制资源
    val ioScope: CoroutineScope
    val uiScope: CoroutineScope

    val injectTypes:Map<String,ServiceType>
}

enum class ServiceType {
    NetWork,DataControl
}