package ink.bluecloud.ui.homeview.fragment.pushdisplaycard

import ink.bluecloud.model.data.HomePagePushCard
import javafx.scene.image.Image
import javafx.scene.paint.Color
import tornadofx.*
import java.text.SimpleDateFormat

class PushDisplayCard:Fragment() {
    private val data = params["data"]as HomePagePushCard
    private var simpleDateFormat = SimpleDateFormat("M-dd")

    override val root = vbox(10) {
        children += generateCover()
        label(data.title)
        label("${data.author}·${simpleDateFormat.format(data.time)}")

        style {
            backgroundColor += Color.WHITE
            backgroundRadius += box(10.px)
        }
        paddingAll = 5
    }

    private fun generateCover() = stackpane {
        imageview(Image(data.cover)) {
            fitWidth = 300.0
            fitHeight = 150.0
        }
    }
}