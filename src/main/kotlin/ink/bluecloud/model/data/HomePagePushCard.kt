package ink.bluecloud.model.data

import java.io.ByteArrayInputStream
import java.time.Duration
import java.util.*

data class HomePagePushCard(
    val title: String,
    val author: String,
    val time: Date,
    val duration: Duration,
    val playVolume: Int,
    val barrageVolume: Int,
    val cover: ByteArrayInputStream
)