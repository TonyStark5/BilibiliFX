package ink.bluecloud.ink.bluecloud.model.networkapi

import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpApi
import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpHeaders
import okhttp3.Headers
import okhttp3.HttpUrl
import java.nio.file.Paths

abstract class NetWorkApiProvider {
    protected val apiFilePath = Paths.get("config\\HttpAPI.proto")
    protected val headersFilePath = Paths.get("config\\APIHeaders.proto")

    abstract fun getHeaders(block: HttpHeaders.() -> Headers): Headers
    abstract fun getAPI(block: HttpApi.() -> HttpUrl): HttpUrl
}