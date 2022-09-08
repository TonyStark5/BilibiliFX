package ink.bluecloud.service.provider.provider

import ink.bluecloud.service.provider.ClientService
import ink.bluecloud.service.provider.ExcludeInjectList
import ink.bluecloud.service.provider.ServiceAutoRelease
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

/**
* 服务控制器的父类，每个服务控制器都会从本类继承
 * 本类提供了参数注入的默认方法，供子类使用或重写
* */
@Suppress("UNCHECKED_CAST")
abstract class ClientServiceProvider : ServiceProvider(){
    fun <T: ClientService> releaseService(service: KClass<T>) {
        serviceMap.remove(service)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun <T: ClientService> provideService(service: KClass<T>, block: T.() -> Unit) {
        if (!isService(service)) throw IllegalArgumentException("错误的服务：该服务提供器无法提供该服务！")
        (serviceMap[service]as? T ?: run {
            instanceService(service, injectArgs).apply {
                if (!service.hasAnnotation<ServiceAutoRelease>()) serviceMap[service] = this@apply
            }
        }).block()
    }

    protected fun <T : ClientService> instanceService(service: KClass<T>, args: Map<String,Any>): T {
        val instance = service.java.getConstructor().newInstance()

        service.findAnnotation<ExcludeInjectList>()?.run {
            ClientService::class.java.declaredFields.forEach {
                if (!clazz.contains(it.type.kotlin)) {
                    it.trySetAccessible()
                    it[instance] = args[it.name]
                }
            }
            return instance ?: throw NullPointerException("指定的服务不存在：${service}")
        }

        ClientService::class.java.declaredFields.forEach {
            it.trySetAccessible()
            it[instance] = args[it.name]
        }

/*
        MethodHandles.privateLookupIn(service, MethodHandles.lookup()).run {
            args.forEach { (k, v) ->
                findVarHandle(service, k, v::class.java).set(instance, v)
            }
        }
*/

        return instance ?: throw NullPointerException("指定的服务不存在：${service}")
    }
}