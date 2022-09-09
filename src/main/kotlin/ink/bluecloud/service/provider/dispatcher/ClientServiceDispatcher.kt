package ink.bluecloud.service.provider.dispatcher

import ink.bluecloud.service.provider.InjectResourcesType
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import ink.bluecloud.service.provider.provider.ServiceProvider
import tornadofx.*
import kotlin.reflect.KClass

/**
 * 服务管理中最终对外暴露的类，该类控制整个程序的服务控制器
 *
 * 外部调用者通过get方法获取对应的服务管理器，然后从服务管理器中获取对应服务
 * 本类的工作是管理全局资源，全局资源通过本类向其他服务提供和管理
 */
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

    init {
        injectTypes.apply {
            this@ClientServiceDispatcher.javaClass.superclass.declaredFields.forEach {
                it.getAnnotation(InjectResourcesType::class.java)?.type?.run {
                    put(it.name, this)
                }
            }
        }
    }
}