package ink.bluecloud.service.clientservice.portal

import com.alibaba.fastjson2.JSONObject
import ink.bluecloud.exceptions.PojoException
import ink.bluecloud.model.data.Video
import ink.bluecloud.model.data.VideoType
import ink.bluecloud.model.pojo.video.portal.PortalVideoJsonRoot
import ink.bluecloud.service.ClientService
import ink.bluecloud.service.provider.InjectByClassified
import ink.bluecloud.service.provider.ServiceAutoRelease
import ink.bluecloud.service.provider.ServiceType
import ink.bluecloud.service.provider.dispatcher.ClientServiceDispatcher


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
        getJsonPOJO(num){
            it.data?.item?.forEach { val0 ->
                val video = Video(
                    avid = val0.id,
                    bvid = val0.bvid,
                    cid = val0.cid,
                    url = val0.uri,
                    cover = val0.pic,
                    title = val0.title,
                    duration = val0.duration,
                    stat = VideoType.Stat(
                        like = val0.stat.like,
                        view = val0.stat.view,
                        danmaku = val0.stat.danmaku
                    ),
                    time = VideoType.Time(
                        publishDate = val0.pubdate
                    )
                )
                videos.add(video)
                handle(video)
            }
        }
        return videos
    }


    /**
     * 获取首页视频推荐 POJO类
     * @param num 首页推荐的视频个数。此参数存在最大值，当超过最大值服务器会自动返回最大个数的视频数
     * @param handle 回调函数，用来处理获取到的POJO类
     */
    fun getJsonPOJO(num: Int = 11, handle: (PortalVideoJsonRoot.Root) -> Unit){
        getPage(num) {
            var result = JSONObject.parseObject(it, PortalVideoJsonRoot.Root::class.java)
            handle(result)
        }
    }

    /**
     * 获取首页视频推荐原始数据
     * @param num 首页推荐的视频个数。此参数存在最大值，当超过最大值服务器会自动返回最大个数的视频数
     * @param handle 回调函数，用来处理获取到的原始数据
     */
    fun getPage(num: Int = 11, handle: (String) -> Unit)=IO{
        httpClient.getFor(
            netWorkResourcesProvider.api.getPortalVideos.newBuilder("?ps=${num}")?.build()!!
        ) {
            val result=body.string()
            handle(result)
        }
    }
}
