package ink.bluecloud.service.clientservice.database

import ink.bluecloud.ink.bluecloud.service.ClientService
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import org.ktorm.database.Database
import java.util.concurrent.atomic.AtomicReference
import kotlin.reflect.KClass

class DataBaseServiceProvider: ClientServiceProvider() {
    private var database = AtomicReference<Database>()
    override fun <T : ClientService> isService(service: KClass<T>) = service.checkService(DataService::class)

    init {
        localInjectArgs["database"] = database
    }
}

abstract class DataService: ClientService() {
    lateinit var database: AtomicReference<Database>
}