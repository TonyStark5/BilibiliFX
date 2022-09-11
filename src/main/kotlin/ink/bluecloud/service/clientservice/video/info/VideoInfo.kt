package ink.bluecloud.service.clientservice.video.info

import ink.bluecloud.service.ClientService
import ink.bluecloud.service.provider.InjectByClassified
import ink.bluecloud.service.provider.ServiceAutoRelease
import ink.bluecloud.service.provider.ServiceType

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