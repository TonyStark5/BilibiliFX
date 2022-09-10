package ink.bluecloud.ui.homeview.fragment.sliderbar

import javafx.animation.Animation.Status.*
import javafx.animation.TranslateTransition
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.util.Duration
import tornadofx.*

class CloudSlideBar(items: Array<String>): StackPane() {
    val selectIndex = SimpleIntegerProperty()
//    private val icon = Font.loadFont(Thread.currentThread().contextClassLoader.getResourceAsStream("css/icon_home.ttf"), 12.0)

    private var disableSlider = false

    init {
        var baseBox:Pane? = null
        borderpane {
            top {
                baseBox = vbox {
                    val myClickHandler = MyClickHandler(disableSlider, selectIndex)
                    val myEnterHandler = MyEnterHandler(selectIndex)
                    val myLeaveHandler = MyLeaveHandler(selectIndex)
                    items.forEachIndexed { index, text ->
                        children += getItem(text,index, myClickHandler,myEnterHandler, myLeaveHandler)
                    }
                }
            }

            bottom = hbox(alignment = Pos.CENTER) {
                label("\uE8C9") {
                    style {
                        textFill = c(95,95,95)
                        fontSize = 25.px
                    }
                }
                paddingBottom = 10
            }
        }

        val region = region {
            maxWidth = 5.0
            maxHeightProperty().bind((baseBox!!.children[0] as HBox).heightProperty())

            style {
                backgroundColor += c(24, 144, 255)
            }
        }

        val transition = TranslateTransition(Duration.millis(250.0), region).apply {
            interpolator = CLOUD_INTERPOLATOR
            statusProperty().addListener { _, _, newValue ->
                disableSlider = when (newValue) {
                    RUNNING -> {
                        true
                    }
                    STOPPED -> {
                        false
                    }
                    PAUSED -> TODO()
                }
            }
        }

        selectIndex.addListener { _, oldValue, newValue ->
            (baseBox!!.children[oldValue.toInt()]as HBox).run {
                background = Background(BackgroundFill(Color.WHITE,null,null))
                children[0].run {
                    style(true) {
                        textFill = c(95,95,95)
                    }
                }
            }

            (baseBox!!.children[newValue.toInt()]as HBox).run {
                background = Background(BackgroundFill(c(230,247,255),null,null))
                children[0].run {
                    style(true) {
                        textFill = c(24,144,255)
                    }
                }
            }

            transition.apply {
                toY = (baseBox!!.children[newValue.toInt()]as HBox).layoutY
            }.play()
        }

        id = "root-pane"
        stylesheets += "css/icon_home.css"

        alignment = Pos.TOP_LEFT
    }

    private fun getItem(title: String, index: Int, myClickHandler: MyClickHandler, myEnterHandler: MyEnterHandler, myLeaveHandler: MyLeaveHandler):HBox {
        return HBox().apply {
            label(title) {
                style {
                    textFill = if (index == 0) {
                        c(24,144,255)
                    } else {
                        c(95,95,95)
                    }
                    fontSize = 25.px
                }
            }

            onMouseClicked = myClickHandler
            onMouseEntered = myEnterHandler
            onMouseExited = myLeaveHandler

            style {
                cursor = Cursor.HAND
                padding = box(15.px,0.px,15.px,10.px)

                if (index == 0) {
                    backgroundColor += c(230,247,255)
                }
            }

            userData = index
        }
    }

    private class MyClickHandler(private val disableSlider: Boolean, private val selectIndex: SimpleIntegerProperty): EventHandler<MouseEvent> {
        override fun handle(event: MouseEvent) {
            if (!disableSlider) selectIndex.value = (event.source as Pane).userData as Int
        }
    }

    private class MyEnterHandler(private val selectIndex: SimpleIntegerProperty): EventHandler<MouseEvent> {
        override fun handle(event: MouseEvent) {
            val index = (event.source as Pane).userData as Int
            if (selectIndex.value == index) return

            (event.source as Pane).background = Background(BackgroundFill(c(230,247,255),null,null))
        }
    }

    private class MyLeaveHandler(private val selectIndex: SimpleIntegerProperty): EventHandler<MouseEvent> {
        override fun handle(event: MouseEvent) {
            val index = (event.source as Pane).userData as Int
            if (selectIndex.value == index) return

            (event.source as Pane).background = Background(BackgroundFill(Color.WHITE,null,null))
        }
    }

}