import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpApi
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun main() {
    Files.write(Paths.get("config\\HttpAPI.proto"), ProtoBuf.encodeToByteArray(HttpApi()), StandardOpenOption.CREATE_NEW)
}