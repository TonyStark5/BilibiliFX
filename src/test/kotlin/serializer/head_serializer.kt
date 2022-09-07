import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import ink.bluecloud.ink.bluecloud.model.networkapi.data.HttpHeaders as HttpHeaders1

var UA_PC = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0"

@Suppress("UncheckedCast")
fun main() {
    val headers = HttpHeaders()

    buildList<Headers> {
        HttpHeaders::class.java.methods.forEach {
            if (it.returnType != HashMap::class.java) return@forEach

            /*
                        it.name.run {
                            substring(3 ..indices.last).run {
                                append("val ${substring(0,1).lowercase()+substring(1.. indices.last)}: Headers,")
                                append('\n')
                            }
                        }
            */
            runCatching {
                if (it.parameters.isEmpty()) {
                    it.invoke(headers) as HashMap<String, String>
                } else {
                    it.invoke(headers,*Array<String>(it.parameterCount) {""}) as HashMap<String, String>
                }
            }.onFailure {
                println(it)
                it.printStackTrace()
            }.onSuccess { map ->
                runCatching {
                    this += map.toHeaders()
                }.onFailure {
                    println(map)
                    println(it.message)
                }
            }
        }
    }.run {
        HttpHeaders1(
            biliUserInfoHeaders = this[0],
            favListHeaders = this[2],
            biliWwwM4sHeaders = this[3],
            logoutHeaders = this[4],
            biliAppJsonAPIHeaders = this[5],
            commonHeaders = this[7],
            headers = this[8],
            biliJsonAPIHeaders = this[9],
            biliAppDownHeaders = this[10],
            biliLoginAuthVaHeaders = this[11],
            biliLoginAuthHeaders = this[12],
            danmuHeaders = this[13],
            allFavListHeaders = this[14],
            biliWwwFLVHeaders = this[15],
            actionHeaders = this[16],
        ).run {
            Files.write(Paths.get("config\\APIHeaders.proto"),ProtoBuf.encodeToByteArray(this),StandardOpenOption.CREATE_NEW)
        }
    }
}

private fun HashMap<String,String>.toHeaders():Headers {
    val builder = Headers.Builder()
    forEach {
        builder.add(it.key,it.value)
    }
    return builder.build()
}