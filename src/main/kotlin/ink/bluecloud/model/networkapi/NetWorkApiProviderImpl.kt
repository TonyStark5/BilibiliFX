package ink.bluecloud.ink.bluecloud.model.networkapi

import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpApi
import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpHeaders
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import okhttp3.Headers
import okhttp3.HttpUrl
import java.nio.file.Files

class NetWorkApiProviderImpl: NetWorkApiProvider() {
    private val headers: HttpHeaders = ProtoBuf.decodeFromByteArray(Files.readAllBytes(headersFilePath))
    private val api: HttpApi = ProtoBuf.decodeFromByteArray(Files.readAllBytes(apiFilePath))

    override fun getHeaders(block: HttpHeaders.() -> Headers): Headers {
        return headers.block()
    }

    override fun getAPI(block: HttpApi.() -> HttpUrl): HttpUrl {
        return api.block()
    }
}