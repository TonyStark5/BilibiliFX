package ink.bluecloud.ink.bluecloud.service.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class ClientService {
    protected lateinit var httpClient: HttpClient
    protected lateinit var netWorkApiProvider: NetWorkApiProvider

    private lateinit var ioScope: CoroutineScope
    private lateinit var uiScope: CoroutineScope

    protected fun UI(block: suspend CoroutineScope.() -> Unit) {
        uiScope.launch {
            block()
        }
    }

    protected fun IO(block: suspend CoroutineScope.() -> Unit) {
        ioScope.launch {
            block()
        }
    }
}