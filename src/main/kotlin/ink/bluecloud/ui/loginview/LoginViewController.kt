package ink.bluecloud.ui.loginview

import com.alibaba.fastjson2.JSONWriter
import ink.bluecloud.cloudnotice
import ink.bluecloud.cloudtools.cloudnotice.CloudNotice
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeType
import ink.bluecloud.ink.bluecloud.service.account.AccountServiceProvider
import ink.bluecloud.ink.bluecloud.service.account.login.LoginService
import ink.bluecloud.ink.bluecloud.service.provider.ClientServiceDispatcher
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.Label
import javafx.scene.control.TreeCell
import javafx.scene.control.TreeView
import javafx.scene.image.Image
import javafx.util.Callback
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