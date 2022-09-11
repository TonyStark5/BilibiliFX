package ink.bluecloud.service.provider.provider

import ink.bluecloud.service.ClientService
import ink.bluecloud.service.provider.*
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.full.*

/**
* 服务控制器的父类，每个服务控制器都会从本类继承
 * 本类提供了参数注入的默认方法，供子类使用或重写
* */
@Suppress("NOTHING_TO_INLINE","UNCHECKED_CAST")
abstract class ClientServiceProvider : ServiceProvider(){
    @OptIn(ExperimentalStdlibApi::class)
    fun <T: ClientService> provideService(service: KClass<T>, block: T.() -> Unit) {
        if (!isService(service)) throw IllegalArgumentException("错误的服务：该服务提供器无法提供该服务！")
        (serviceMap[service]as? T ?: run {
            instanceService(service, injectArgs,localInjectArgs).apply {
                if (!service.hasAnnotation<ServiceAutoRelease>()) serviceMap[service] = this@apply
            }
        }).block()
    }

    fun <T: ClientService> releaseService(service: KClass<T>) {
        serviceMap.remove(service)
    }

    private val methodHandles = MethodHandles.lookup()
    private fun <T : ClientService> instanceService(service: KClass<T>, injectArgs: Map<String,Any>, localInjectArgs: Map<String,Any>): T {
        val instance = methodHandles.findConstructor(service.java, MethodType.methodType(Void.TYPE)).invoke()as? T

        service.allSuperclasses.forEach {
            if (it == Any::class) return@forEach

            service.findAnnotation<InjectListExcluding>()?.run {
                it.java.declaredFields.forEach { field ->
                    if (!clazz.contains(field.type.kotlin)) doInject(field, injectArgs, instance)
                }
            }

            service.findAnnotation<InjectAllResource>()?.run {
                it.java.declaredFields.forEach { field ->
                    doInject(field, injectArgs, instance)
                }
            }

            service.findAnnotation<InjectListOnly>()?.run {
                it.java.declaredFields.forEach { field ->
                    firstInject(field, injectArgs, instance)
                    if (clazz.contains(field.type.kotlin)) doInject(field, injectArgs, instance)
                }
            }

            service.findAnnotation<InjectByClassified>()?.run {
                it.java.declaredFields.forEach { field ->
                    firstInject(field, injectArgs, instance)
                    if (injectTypes.contains(field.name) && injectTypes[field.name] in type) doInject(field, injectArgs, instance)
                }
            }

            it.java.declaredFields.forEach { field ->
                doInject(field, localInjectArgs, instance)
            }
        }

        return instance ?: throw NullPointerException("指定的服务不存在：${service}")
    }

    protected fun KClass<out ClientService>.checkService(service: KClass<out ClientService>):Boolean {
        return this == service || superclasses.contains(service)
    }

    private inline fun <T : ClientService> firstInject(
        field: Field,
        injectArgs: Map<String, Any>,
        instance: T?
    ) {
        if (field.name == "ioScope" ||field.name == "uiScope") {
            doInject(field, injectArgs, instance)
        }
    }

    private inline fun <T : ClientService> doInject(
        field: Field,
        injectArgs: Map<String, Any>,
        instance: T?
    ) {
        methodHandles.unreflectVarHandle(field).run {
            injectArgs[field.name]?.run { set(instance, this) }
        }
    }
}