package ink.bluecloud.ui.homeview.fragment.pushdisplaycard

import ink.bluecloud.model.data.HomePagePushCard
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import tornadofx.*
import java.text.SimpleDateFormat

class PushDisplayCard:Fragment() {
    private val data = params["data"]as HomePagePushCard
    private var simpleDateFormat = SimpleDateFormat("M-dd")

    override val root = vbox(10) {
        children += generateCover()

        vbox {
            label(data.title)
            label("${data.author}·${simpleDateFormat.format(data.time)}")

            padding = insets(10, 5, 5, 5)
        }

        style {
            backgroundColor += Color.WHITE
        }

        heightProperty().addListener { _, _, newValue ->
            clip = Rectangle(300.0, newValue.toDouble()).apply {
                arcWidth = 10.0
                arcHeight = 10.0
            }
        }
    }

    private fun generateCover() = stackpane {
        imageview(Image(data.cover)) {
            fitWidth = 300.0
            fitHeight = 150.0
        }
    }
}