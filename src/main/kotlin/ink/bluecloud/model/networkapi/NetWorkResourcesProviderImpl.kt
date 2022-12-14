package ink.bluecloud.model.networkapi

import ink.bluecloud.model.networkapi.data.HttpApi
import ink.bluecloud.model.networkapi.data.HttpHeaders
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import okhttp3.Cookie
import java.nio.file.Files

@OptIn(ExperimentalSerializationApi::class)
class NetWorkResourcesProviderImpl: NetWorkResourcesProvider() {
    override val headers: HttpHeaders = ProtoBuf.decodeFromByteArray(Files.readAllBytes(headersFilePath))
    override val api: HttpApi = ProtoBuf.decodeFromByteArray(Files.readAllBytes(apiFilePath))
    override val cookieManager: Cookie
        get() = TODO("Not yet implemented")
}