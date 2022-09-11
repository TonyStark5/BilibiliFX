package ink.bluecloud.ui.homeview.fragment.pushdisplaycard

import ink.bluecloud.model.data.HomePagePushCard
import ink.bluecloud.ui.FONT_SIZE
import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.shape.Rectangle
import tornadofx.*
import java.text.SimpleDateFormat

class PushDisplayCard(private val data: HomePagePushCard):VBox(10.0) {
    private var simpleDateFormat = SimpleDateFormat("M-dd")

    init {
        children += generateCover()

        vbox(5) {
            label(data.title) {
                style {
                    fontFamily = FONT_SIZE.BOLD
                }
            }
            label("${data.author}·${simpleDateFormat.format(data.time)}")

            padding = insets(5)
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

    private fun generateCover() = StackPane().apply {
        val cover = imageview(Image(data.cover)) {
            fitWidth = 300.0
            fitHeight = 150.0
        }

        rectangle {
            fill = LinearGradient(
                1.0, 1.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE,
                Stop(0.0, Color(0.0, 0.0, 0.0, 0.65)),
                Stop(1.0, Color(1.0, 1.0, 1.0, 0.0))
            )

            widthProperty().bind(cover.fitWidthProperty())
            heightProperty().bind(Bindings.createDoubleBinding({
                cover.fitHeight / 4.0
            },cover.fitHeightProperty()))

            StackPane.setAlignment(this,Pos.BOTTOM_CENTER)
        }

        borderpane {
            bottom = borderpane {
                left = hbox(5) {
                    label("播放量${data.playVolume}") {
                        style {
                            textFill = Color.WHITE
                        }
                    }

                    label("弹幕量${data.barrageVolume}") {
                        style {
                            textFill = Color.WHITE
                        }
                    }
                }

                right = label("${data.duration.toMinutesPart()}:${data.duration.toSecondsPart()}") {
                    style {
                        textFill = Color.WHITE
                    }
                }
            }
            padding = insets(0,5,5,5)
        }
    }
}