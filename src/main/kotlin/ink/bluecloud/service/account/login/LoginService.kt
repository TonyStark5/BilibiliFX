package ink.bluecloud.ink.bluecloud.service.account.login

import com.alibaba.fastjson2.parseObject
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import ink.bluecloud.ink.bluecloud.service.account.AccountService
import ink.bluecloud.ink.bluecloud.service.provider.ServiceAutoRelease
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@ServiceAutoRelease
class LoginService: AccountService() {
    private lateinit var authKey:String
    lateinit var qrCode:String

    fun getCode(block: ByteArrayInputStream.() -> Unit) = IO {
        httpClient.getFor(
            netWorkApiProvider.getHeaders {
                biliLoginAuthHeaders
            },
            netWorkApiProvider.getAPI {
                getLoginQRCode
            },
        ) {
            body.string().parseObject().getJSONObject("data").run {
                authKey = getString("url")
                getString("oauthKey").genericQRCode(300).run {
                    UI { block() }
                }
            }
        }
    }
}