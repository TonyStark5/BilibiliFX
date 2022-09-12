package ink.bluecloud.ui.fragment

import com.sun.jna.NativeLibrary
import okhttp3.*
import okio.IOException
import tornadofx.*
import uk.co.caprica.vlcj.binding.RuntimeUtil
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface
import uk.co.caprica.vlcj.media.callback.DefaultCallbackMedia
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import java.io.InputStream

class VideoPlayer:Fragment() {
    private val embeddedMediaPlayer: EmbeddedMediaPlayer/* = MediaPlayerFactory().mediaPlayers().newEmbeddedMediaPlayer()*/
    val okHttpClient = OkHttpClient()


    init {
        // for debug
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "libs\\vlc-lib")
        embeddedMediaPlayer = MediaPlayerFactory().mediaPlayers().newEmbeddedMediaPlayer()
    }
    override val root = stackpane {
//        MediaVideo()
        val view = imageview {
            isPreserveRatio = true
        }
        embeddedMediaPlayer.videoSurface().set(ImageViewVideoSurface(view))

/*        executeRequest(Request("https://upos-sz-mirror08ct.bilivideo.com/upgcxcode/16/06/827140616/827140616-1-208.mp4?e=ig8euxZM2rNcNbhgnWdVhwdlhzNHhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1662986377&gen=playurlv2&os=08ctbv&oi=1785775206&trid=102f0d9a3ddd45e0a5f3d622311c6f77T&mid=0&platform=html5&upsig=415c8500e28a92ffc8ed8cae26e2fe03&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,mid,platform&bvc=vod&nettype=0&bw=329671&orderid=0,1&logo=80000000".toHttpUrl())) {
//            embeddedMediaPlayer.media().play(MediaVideo(body.byteStream()))
        }*/
//        embeddedMediaPlayer.media().play(MediaVideo(FileInputStream("D:\\迅雷下载\\Bilibili\\洛少爷\\112.mp4")))
//        embeddedMediaPlayer.media().play(RandomAccessFileMedia(File("D:\\迅雷下载\\Bilibili\\洛少爷\\112.mp4")))
        embeddedMediaPlayer.media().play("D:\\迅雷下载\\Bilibili\\洛少爷\\112.mp4")

        primaryStage.setOnCloseRequest {
            embeddedMediaPlayer.controls().stop()
            embeddedMediaPlayer.release()
        }
    }

    private fun executeRequest(
        request: Request,
        onResponse: Response.(Call) -> Unit
    ) {
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.onResponse(call)
            }
        })
    }

    class MediaVideo(private val inputStream: InputStream): DefaultCallbackMedia(true) {
        override fun onGetSize(): Long {
            return inputStream.available().toLong()
        }

        override fun onOpen(): Boolean {
            return true
        }

        override fun onRead(buffer: ByteArray?, bufferSize: Int): Int {
            return inputStream.read(buffer, 0, bufferSize)
        }

        override fun onSeek(offset: Long): Boolean {
            return true
        }

        override fun onClose() {
            inputStream.close()
        }
    }
}