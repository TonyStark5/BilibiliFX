package ink.bluecloud.ink.bluecloud.service.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProvider
import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProviderImpl
import ink.bluecloud.ink.bluecloud.service.account.AccountServiceProvider
import ink.bluecloud.ink.bluecloud.service.account.login.LoginService
import ink.bluecloud.ink.bluecloud.service.net.HttpClientImpl
import tornadofx.*
import java.lang.invoke.MethodHandles
import kotlin.reflect.KClass


class ClientServiceDispatcher: Controller() {
    private val serviceMap = HashMap<KClass<out ClientServiceProvider>,ClientServiceProvider>()

    private val httpClient = HttpClientImpl()
    private val netWorkApiProvider = NetWorkApiProviderImpl()

    @Suppress("UNCHECKED_CAST")
    operator fun <T: ClientServiceProvider> get(provider: KClass<T>): T {
        return serviceMap[provider]as? T ?: synchronized(this) {
            provider.java.instanceService().apply {
                serviceMap[provider] = this
            }as T
        }
    }

    private fun Class<out ClientServiceProvider>.instanceService(): ClientServiceProvider {
        val instance = getConstructor(
            HttpClient::class.java,
            NetWorkApiProvider::class.java
        ).newInstance(
            httpClient,
            netWorkApiProvider
        )

        return instance ?: throw NullPointerException("指定的服务不存在：${simpleName}")
    }
}