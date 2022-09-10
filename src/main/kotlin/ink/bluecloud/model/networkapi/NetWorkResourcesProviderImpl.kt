package ink.bluecloud.model.networkapi

import ink.bluecloud.model.networkapi.data.HttpApi
import ink.bluecloud.model.networkapi.data.HttpHeaders
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.nio.file.Files

@OptIn(ExperimentalSerializationApi::class)
class NetWorkResourcesProviderImpl: NetWorkResourcesProvider() {
    override val headers: HttpHeaders = ProtoBuf.decodeFromByteArray(Files.readAllBytes(headersFilePath))
    override val api: HttpApi = ProtoBuf.decodeFromByteArray(Files.readAllBytes(apiFilePath))
}