package ink.bluecloud.ink.bluecloud.service.clientservice.account

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import ink.bluecloud.ink.bluecloud.service.provider.ClientService
import ink.bluecloud.ink.bluecloud.service.provider.provider.ClientServiceProvider
import java.io.ByteArrayOutputStream
import kotlin.reflect.KClass
import kotlin.reflect.full.superclasses

/**
* 账户相关服务提供器
 *
* 通过本提供器，程序获得对用户账户的控制功能
* */
class AccountServiceProvider: ClientServiceProvider() {
    private val accountData = AccountData("","", charArrayOf('1'))
    override fun <T : ClientService> isService(service: KClass<T>) = service.superclasses.contains(AccountService::class)

    init {
        injectArgs["accountData"] = accountData
    }
}

/**
* 账户服务
*/
abstract class AccountService: ClientService() {
    lateinit var accountData: AccountData

    protected fun String.genericQRCode(size: Int) = ByteArrayOutputStream(500).apply {
        MatrixToImageWriter.writeToStream(
            QRCodeWriter().encode(this@genericQRCode, BarcodeFormat.QR_CODE, size, size),
            "png",
            this
        )
    }.toByteArray().inputStream()
}

data class AccountData(
    val name:String,
    val uuid: String,
    val passwd: CharArray
)