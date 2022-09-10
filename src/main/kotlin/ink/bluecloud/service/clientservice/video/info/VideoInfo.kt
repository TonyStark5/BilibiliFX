package ink.bluecloud.ink.bluecloud.service.clientservice.video.info

import com.alibaba.fastjson2.JSONObject
import ink.bluecloud.ink.bluecloud.exceptions.PojoException

import ink.bluecloud.ink.bluecloud.model.pojo.video.portal.PortalVideoJsonRoot
import ink.bluecloud.ink.bluecloud.service.ClientService
import ink.bluecloud.ink.bluecloud.service.provider.ServiceType
import ink.bluecloud.model.pojo.video.info.VideoInfoJsonRoot
import ink.bluecloud.ink.bluecloud.model.data.Video
import ink.bluecloud.service.provider.InjectByClassified
import ink.bluecloud.service.provider.ServiceAutoRelease
import java.lang.Exception

@ServiceAutoRelease
@InjectByClassified(ServiceType.NetWork)
class VideoInfo : ClientService() {
//    fun getInfo(bvid: String, handle: (video: Video) -> Unit) {
//        httpClient.getFor(
//            netWorkApiProvider.api.getVideoINFO.newBuilder("?bvid=${bvid}")?.build()!!
//        ) {
//            try {
//                val root = JSONObject.parseObject(body.string(), VideoInfoJsonRoot.Root::class.java)
//                root.data.let { val0 ->
//                    Video(
//                        avid = val0.aid,
//                        bvid = val0.bvid,
//                        cid = val0.cid,
//                        cover=val0.pic,
//                        time = Time(time = 0)
//
//                    )
//                }
////                handle(video)
//
//            } catch (e: Exception) {
//                throw PojoException()
//            }
//
//        }
//    }
}