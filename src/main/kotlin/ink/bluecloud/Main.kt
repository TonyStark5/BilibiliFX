package ink.bluecloud

import javafx.application.Application

fun main(args: Array<String>) {
    System.setProperty("prism.lcdtext", "false")
    Application.launch(MainApp::class.java,*args)
}

