package maintest

import ink.bluecloud.ink.bluecloud.service.ClientService
import ink.bluecloud.ink.bluecloud.service.provider.ServiceType
import ink.bluecloud.service.clientservice.account.AccountService
import ink.bluecloud.service.provider.InjectByClassified
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import kotlin.reflect.KClass

val dispatcher = ClientServiceDispatcher()

fun main() {
    dispatcher[TestProvider::class].provideService(MainTest::class) {
        doSome()
    }
}

class TestProvider:ClientServiceProvider() {
    override fun <T : ClientService> isService(service: KClass<T>) = service.checkService(AccountService::class)
}

@InjectByClassified(ServiceType.NetWork)
class MainTest: ClientService() {
    fun doSome() {
        println(httpClient)
        println(netWorkApiProvider.api.getLoginQRCode)
    }
}