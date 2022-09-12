package ink.bluecloud.ui

import javafx.animation.Timeline

val FONT_SIZE = FontSize()

data class FontSize(
    val DEFAULT:String = "HarmonyOS Sans SC",
    val MEDIUM:String = "HarmonyOS Sans SC Medium",
    val BOLD:String = "HarmonyOS Sans SC Bold"
)

fun timeline(play: Boolean = true, op: (Timeline).() -> Unit) = Timeline(165.0).apply {
    op()
    if (play) play()
}