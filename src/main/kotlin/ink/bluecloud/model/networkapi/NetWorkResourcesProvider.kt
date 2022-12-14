package ink.bluecloud.model.networkapi

import ink.bluecloud.model.networkapi.data.HttpApi
import ink.bluecloud.model.networkapi.data.HttpHeaders
import okhttp3.Cookie
import java.nio.file.Paths

abstract class NetWorkResourcesProvider {
    protected val apiFilePath = Paths.get("config\\HttpAPI.proto")
    protected val headersFilePath = Paths.get("config\\APIHeaders.proto")

    abstract val headers: HttpHeaders
    abstract val api: HttpApi
    abstract val cookieManager: Cookie
}