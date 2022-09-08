package ink.bluecloud.ui.loginview

import com.alibaba.fastjson2.JSONWriter
import ink.bluecloud.cloudnotice
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeType
import ink.bluecloud.cloudtools.stageinitializer.TitleBar
import ink.bluecloud.ink.bluecloud.service.clientservice.release.ReleaseServiceProvider
import ink.bluecloud.ink.bluecloud.service.clientservice.release.login.ReleaseService
import ink.bluecloud.service.clientservice.account.AccountServiceProvider
import ink.bluecloud.service.clientservice.account.login.LoginService
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import javafx.application.Platform
import javafx.scene.image.Image
import tornadofx.*

class LoginViewController: Controller() {
    private val dispatcher by inject<ClientServiceDispatcher>()

    val view = find<LoginView>().apply {
        (root.top as TitleBar).onCloseRequest = {
            dispatcher[ReleaseServiceProvider::class].provideService(ReleaseService::class) {
                onExit()
                Platform.exit()
            }
        }

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