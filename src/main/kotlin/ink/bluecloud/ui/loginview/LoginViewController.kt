package ink.bluecloud.ui.loginview

import com.alibaba.fastjson2.JSONWriter
import ink.bluecloud.cloudnotice
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeType
import ink.bluecloud.service.clientservice.account.AccountServiceProvider
import ink.bluecloud.service.clientservice.account.login.LoginService
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import javafx.scene.image.Image
import tornadofx.*

class LoginViewController: Controller() {
    private val dispatcher by inject<ClientServiceDispatcher>()

    val view = find<LoginView>().apply {
        dispatcher[AccountServiceProvider::class].provideService(LoginService::class) {
            getCode {
                qrCodeBox.image = Image(this)

                whenSuccess {
                    cloudnotice(NoticeType.Right, "登录成功！")
                    println(toJSONString(JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.WriteMapNullValue))
                }
            }
        }
    }
}