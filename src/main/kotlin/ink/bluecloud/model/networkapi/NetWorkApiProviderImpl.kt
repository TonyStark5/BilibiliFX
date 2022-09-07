package ink.bluecloud.ink.bluecloud.model.networkapi

import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpApi
import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpHeaders
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import okhttp3.Headers
import okhttp3.HttpUrl
import java.nio.file.Files

class NetWorkApiProviderImpl: NetWorkApiProvider() {
    override val headers: HttpHeaders = ProtoBuf.decodeFromByteArray(Files.readAllBytes(headersFilePath))
    override val api: HttpApi = ProtoBuf.decodeFromByteArray(Files.readAllBytes(apiFilePath))
}