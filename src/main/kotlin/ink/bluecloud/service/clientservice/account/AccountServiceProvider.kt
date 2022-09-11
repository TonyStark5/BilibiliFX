package ink.bluecloud.service.clientservice.account

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import ink.bluecloud.service.ClientService
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.reflect.KClass

/**
* 账户相关服务提供器
 *
* 通过本提供器，程序获得对用户账户的控制功能
* */
abstract class AccountServiceProvider: ClientServiceProvider() {
    abstract val accountData: AccountData

    override fun <T : ClientService> isService(service: KClass<T>) = service.checkService(AccountService::class)
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
    val name:SimpleStringProperty,
    val uuid: SimpleStringProperty,
    val passwd: SimpleObjectProperty<ByteArrayInputStream>
)