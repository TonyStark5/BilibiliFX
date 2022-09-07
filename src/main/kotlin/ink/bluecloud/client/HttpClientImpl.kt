package ink.bluecloud.ink.bluecloud.service.net

import ink.bluecloud.client.HttpClient
import okhttp3.*
import okio.IOException

class HttpClientImpl: HttpClient() {
    override fun getFor(
        headers: Headers,
        url: HttpUrl,
        onFailure: (Call.(IOException) -> Unit)?,
        onResponse: Response.(Call) -> Unit
    ) {
        httpClient.newCall(Request(url, headers)).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure?.run {
                    call.onFailure(e)
                }?: call.defaultOnFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.onResponse(call)
            }
        })
    }

    override fun postFor(
        headers: Headers,
        url: HttpUrl,
        params: Map<String,String>,
        onFailure: (Call.(IOException) -> Unit)?,
        onResponse: Response.(Call) -> Unit
    ) {
        val requestBody = FormBody.Builder().apply {
            params.forEach { (k, v) ->
                add(k,v)
            }
        }.build()

        val request = Request.Builder()
            .headers(headers)
            .url(url)
            .post(requestBody)
            .build()
        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure?.run {
                    call.onFailure(e)
                }?: call.defaultOnFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.onResponse(call)
            }
        })

    }

}