package ink.bluecloud

import com.sun.jna.NativeLibrary
import javafx.application.Application
import uk.co.caprica.vlcj.binding.RuntimeUtil

fun main(args: Array<String>) {
    NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "libs\\vlc-lib")
    System.setProperty("prism.lcdtext", "false")
    Application.launch(MainApp::class.java,*args)
}

