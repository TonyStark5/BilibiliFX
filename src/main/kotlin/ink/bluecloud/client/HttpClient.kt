package ink.bluecloud.client

import ink.bluecloud.model.networkapi.NetWorkApiProviderImpl
import okhttp3.*
import okio.IOException

/**
* Http工具类，提供了post和get两种方法的请求
* */
abstract class HttpClient: Client() {
    protected val httpClient = OkHttpClient()
    override val apiProvider = NetWorkApiProviderImpl()

    init {
        httpClient.dispatcher.executorService
    }
    abstract fun getFor(
        url: HttpUrl,
        headers: Headers? = defaultHeader,
        onFailure: (Call.(IOException) -> Unit)? = null,
        onResponse: Response.(Call) -> Unit
    )

    abstract fun postFor(
        url: HttpUrl,
        params: Map<String,String>,
        headers: Headers? = defaultHeader,
        onFailure: (Call.(IOException) -> Unit)? = null,
        onResponse: Response.(Call) -> Unit
    )

    protected val defaultOnFailure: Call.(IOException) -> Unit by lazy {
        {
            it.printStackTrace()
        }
    }
}