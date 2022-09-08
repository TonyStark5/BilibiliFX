package ink.bluecloud.client

import ink.bluecloud.model.networkapi.NetWorkApiProvider
import okhttp3.Headers

abstract class Client {
    abstract val apiProvider: NetWorkApiProvider

    protected val defaultHeader = Headers.Builder()
        .add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
        .add("referer", "www.bilibili.com")
        .build()
}