package ink.bluecloud.service.clientservice.bapi.data
/**
 * 视频单元类型：屏蔽直接与底层pojo直接交互
 */
data class Video(
    /**
     * 视频ID，通常为AVID
     */
    val id: Long,
    /**
     * 视频的AVID可与BVID互相转换
     * 视频的唯一标识符，可以标记一个多p视频，或一个多片段视频
     */
    val avid: Long,
    /**
     * 视频的BVID可与AVID互相转换
     * 视频的唯一标识符，可以标记一个多p视频，或一个多片段视频
     */
    val bvid: String,
    /**
     * 视频的CID列表
     * 视频有多少分p 就有多少CID，每一个分p 就对应一个唯一的CID。
     * 此字段在部分情况下都为null，因此想要获取不为null 的CID 列表请 使用方法: toCids()
     */
    val cids: List<Long>?,
    /**
     * 视频当前分p的标识，通常为视频第一个分P的标识。
     * 即便是有多个分P，或者只有一个分p的视频
     */
    val cid: Long,
    /**
     * 视频的URL，通常为：https://www.bilibili.com/video/{BVID}
     */
    val url: String,
    /**
     * 视频封面的URL
     */
    val cover: String,
    /**
     * 视频的标题
     */
    val title: String,
) {
    /**
     * 获取视频所有的cid，该方法将等待
     */
    fun toCids(): List<Long>? {
        if (cids != null) return cids
        return cids
    }
}