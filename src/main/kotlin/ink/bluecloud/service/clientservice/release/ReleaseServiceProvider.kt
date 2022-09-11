package ink.bluecloud.service.clientservice.release

import ink.bluecloud.service.ClientService
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import kotlin.reflect.KClass
import kotlin.reflect.full.superclasses

/**
* 账户相关服务提供器
 *
* 通过本提供器，程序获得对用户账户的控制功能
* */
class ReleaseServiceProvider: ClientServiceProvider() {
    override fun <T : ClientService> isService(service: KClass<T>) = service.superclasses.contains(ClientService::class)
}