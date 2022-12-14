package ink.bluecloud

import ink.bluecloud.cloudtools.cloudnotice.CloudNotice
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeType
import ink.bluecloud.cloudtools.stageinitializer.initCustomizeStageAndRoot
import ink.bluecloud.css.themes
import ink.bluecloud.ui.homeview.HomeView
import ink.bluecloud.ui.homeview.HomeViewController
import ink.bluecloud.ui.loginview.LoginViewController
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.WindowEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import tornadofx.*

class MainApp: App(
    icon = Image("icon.png"),
//    primaryView = LoginView::class,
    primaryView = HomeView::class,
) {

/*
    init {
        reloadViewsOnFocus()
        reloadStylesheetsOnFocus()
    }
*/

    override fun start(stage: Stage) {
        super.start(stage.apply {
            //资源预热
            addEventHandler(WindowEvent.WINDOW_SHOWN) {
                CoroutineScope(Dispatchers.JavaFx).launch {
                    find<LoginViewController>()
                    find<HomeViewController>()
                }
            }
        }.initCustomizeStageAndRoot(15))
    }

    override fun createPrimaryScene(view: UIComponent): Scene {
        return super.createPrimaryScene(view).apply {
            themes {
                this += it.globalCssFile
                this += it.buttonCssFile
            }
            stylesheets += "css/font/font.css"
        }
    }
}

inline fun Controller.cloudnotice(type: NoticeType, message: String) = CloudNotice(type, message, primaryStage).show()