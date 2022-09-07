package ink.bluecloud.ink.bluecloud.service.account

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import ink.bluecloud.client.HttpClient
import ink.bluecloud.ink.bluecloud.model.networkapi.NetWorkApiProvider
import ink.bluecloud.ink.bluecloud.service.provider.ClientService
import ink.bluecloud.ink.bluecloud.service.provider.ClientServiceProvider
import java.io.ByteArrayOutputStream
import kotlin.reflect.KClass
import kotlin.reflect.full.superclasses

class AccountServiceProvider(httpClient: HttpClient, netWorkApiProvider: NetWorkApiProvider) : ClientServiceProvider(httpClient, netWorkApiProvider) {

    private val accountData = AccountData("","", charArrayOf('1'))

    override fun <T : ClientService> isService(service: KClass<T>) = service.superclasses.contains(AccountService::class)

    init {
        injectArgs["accountData"] = accountData
    }
}

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
