package ink.bluecloud.ink.bluecloud.service.provider

import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProvider
import kotlin.reflect.KClass
import kotlin.reflect.full.hasAnnotation

@Suppress("UNCHECKED_CAST")
abstract class ClientServiceProvider(httpClient: HttpClient, netWorkApiProvider: NetWorkApiProvider) : ServiceProvider(httpClient, netWorkApiProvider){
    fun <T: ClientService> releaseService(service: KClass<T>) {
        serviceMap.remove(service)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun <T: ClientService> provideService(service: KClass<T>, block: T.() -> Unit) {
        if (!isService(service)) throw IllegalArgumentException("错误的服务：该服务提供器无法提供该服务！")
        (serviceMap[service]as? T ?: run {
            instanceService(service.java, injectArgs).apply {
                if (!service.hasAnnotation<ServiceAutoRelease>()) serviceMap[service] = this@apply
            }
        }).block()
    }

    protected fun <T : ClientService> instanceService(service: Class<T>, args: Map<String,Any>): T {
        val instance = service.getConstructor().newInstance()
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

/**
*
* 在服务中提供此注解，在View使用该服务的时候将不会将其缓存，在调用结束后GC
*/
@Target(AnnotationTarget.CLASS)
annotation class ServiceAutoRelease