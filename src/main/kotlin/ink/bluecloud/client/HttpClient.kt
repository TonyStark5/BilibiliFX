package ink.bluecloud.client

import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProviderImpl
import okhttp3.*
import okhttp3.internal.platform.Platform
import okio.IOException
import kotlin.concurrent.thread

abstract class HttpClient: Client() {
    protected val httpClient = OkHttpClient()
    override val apiProvider = NetWorkApiProviderImpl()

    abstract fun getFor(
        headers: Headers,
        url: HttpUrl,
        onFailure: (Call.(IOException) -> Unit)? = null,
        onResponse: Response.(Call) -> Unit
    )

    abstract fun postFor(
        headers: Headers,
        url: HttpUrl,
        params: Map<String,String>,
        onFailure: (Call.(IOException) -> Unit)?,
        onResponse: Response.(Call) -> Unit
    )

    protected val defaultOnFailure: Call.(IOException) -> Unit by lazy {
        {
            it.printStackTrace()
        }
    }
}