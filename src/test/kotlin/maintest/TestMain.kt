package maintest

import ink.bluecloud.ink.bluecloud.service.ClientService
import ink.bluecloud.ink.bluecloud.service.clientservice.portal.PortalVideoList
import ink.bluecloud.ink.bluecloud.service.provider.ServiceType
import ink.bluecloud.service.provider.InjectByClassified
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import kotlin.reflect.KClass

val dispatcher = ClientServiceDispatcher()

fun main() {
    dispatcher[TestProvider::class].provideService(PortalVideoList::class) {
       getPage {
           println(it)
       }

        println(6666)
    }
}

class TestProvider:ClientServiceProvider() {
    override fun <T : ClientService> isService(service: KClass<T>) = service.checkService(PortalVideoList::class)
}

@InjectByClassified(ServiceType.NetWork)
//@InjectAllResources
class MainTestService: ClientService() {
    fun doSome() = IO {
        println(httpClient)
        println(netWorkApiProvider.api.getLoginQRCode)
    }
}