package ink.bluecloud.ink.bluecloud.service.provider.dispatcher

import ink.bluecloud.ink.bluecloud.service.provider.ClientServiceProvider
import ink.bluecloud.ink.bluecloud.service.provider.ServiceProvider
import tornadofx.*
import kotlin.reflect.KClass

class ClientServiceDispatcher:ServiceDispatcher() {
    @Suppress("UNCHECKED_CAST")
    operator fun <T: ClientServiceProvider> get(provider: KClass<T>): T {
        return serviceMap[provider]as? T ?: synchronized(this) {
            provider.java.instanceService().apply {
                serviceMap[provider] = this
            }as T
        }
    }

    private fun Class<out ClientServiceProvider>.instanceService(): ClientServiceProvider {
        val instance = getConstructor().newInstance()
        ServiceProvider::class.java.declaredFields.forEach {
            injectArgs[it.name]?.run {
                it.trySetAccessible()
                it[instance] = this
            }

            if (it.name == "injectArgs") {
                it.trySetAccessible()
                it[instance] = (it[instance]as HashMap<*, *>).plus(injectArgs)
            }
        }

        return instance ?: throw NullPointerException("指定的服务不存在：${simpleName}")
    }
}