package ink.bluecloud.ink.bluecloud.service.clientservice.portal

import com.alibaba.fastjson2.JSONObject
import ink.bluecloud.ink.bluecloud.exceptions.PojoException
import ink.bluecloud.ink.bluecloud.model.pojo.video.portal.PortalVideoJsonRoot
import ink.bluecloud.service.provider.ServiceAutoRelease
import okhttp3.HttpUrl.Companion.toHttpUrl
import ink.bluecloud.ink.bluecloud.service.ClientService
import ink.bluecloud.ink.bluecloud.service.provider.ServiceType
import ink.bluecloud.service.clientservice.bapi.data.Video
import ink.bluecloud.service.provider.InjectByClassified
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher
import ink.bluecloud.service.provider.provider.ClientServiceProvider
import java.lang.Exception

import kotlin.reflect.KClass
import kotlin.reflect.full.superclasses


val dispatcher = ClientServiceDispatcher()

/**
 * 获取首页视频推荐 API
 * @API https://api.bilibili.com/x/web-interface/index/top/feed/rcmd?ps=1
 */
@ServiceAutoRelease
@InjectByClassified(ServiceType.NetWork)
class PortalVideoList : ClientService() {

    /**
     * 获取首页视频推荐
     * @param num 首页推荐的视频个数。此参数存在最大值，当超过最大值服务器会自动返回最大个数的视频数
     * @param handle 回调函数，用来处理当前已经被解析出来的视频单元
     * @return ArrayList<Video> 该方法为异步方法，因此参数会立即返回但是会在接下来一段之间内将视频单元填装进去。暂停线程3s打印该返回值可验证
     * @exception PojoException 将会抛出一个POJO解析异常，此时通常是服务器返回的JSON 字段发生了变动导致的不兼容
     */
    fun getVideoList(num: Int = 11, handle: (Video) -> Unit): ArrayList<Video> {
        val videos = ArrayList<Video>()
        //        netWorkApiProvider.api.getPortalVideos.newBuilder("?ps=${num}")
        httpClient.getFor(
            netWorkApiProvider.api.getPortalVideos.newBuilder("?ps=${num}")?.build()!!
        ) {
            try {
                val root = JSONObject.parseObject(body.string(), PortalVideoJsonRoot.Root::class.java)
                root.data?.item?.forEach { val0 ->
                    val video = Video(
                        id = val0.id,
                        avid = val0.id,
                        bvid = val0.bvid,
                        cid = val0.cid,
                        url = val0.uri,
                        cover = val0.pic,
                        title = val0.title,
                        cids = null
                    )
                    videos.add(video)
                    handle(video)
                }
            } catch (e: Exception) {
                throw PojoException()
            }
        }
        return videos
    }
}
