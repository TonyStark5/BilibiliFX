package ink.bluecloud.ink.bluecloud.service.clientservice.account.login

import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.parseObject
import ink.bluecloud.ink.bluecloud.service.clientservice.account.AccountService
import ink.bluecloud.ink.bluecloud.service.provider.ServiceAutoRelease
import kotlinx.coroutines.delay
import java.io.ByteArrayInputStream

@ServiceAutoRelease
class LoginService: AccountService() {
    private lateinit var authKey:String
    lateinit var qrCode:String

    fun getCode(block: ByteArrayInputStream.() -> Unit) = IO {
        httpClient.getFor(
            netWorkApiProvider.headers.biliLoginAuthHeaders,
            netWorkApiProvider.api.getLoginQRCode,
        ) {
            body.string().parseObject().getJSONObject("data").run {
                authKey = getString("oauthKey")
                getString("url").genericQRCode(300).run {
                    UI { block() }
                }
            }
        }
    }

    fun whenSuccess(block: JSONObject.() -> Unit) = IO {
        var json: JSONObject? = null

        while (true) {
            httpClient.postFor(
                netWorkApiProvider.headers.biliLoginAuthVaHeaders,
                netWorkApiProvider.api.getLoginStatus,
                mapOf("oauthKey" to authKey, "gourl" to "https://www.bilibili.com/")
            ) {
                json = body.string().parseObject()
            }

            if (json?.getBoolean("status") == true) {
                UI {
                    json?.block() ?: throw IllegalArgumentException("意外的参数：JsonObject")
                }
                break
            } else {
                delay(500)
            }
        }
    }
}