package ink.bluecloud.ink.bluecloud.model.data

/**
 * 视频单元类型：屏蔽直接与底层pojo直接交互
 * 视频单元只专注于 视频类型 对于其他类型以及引用将会以其ID形式存在，如：
 *     - 作者：不会存在关于作者的所有信息，只会存在关于这个字段最主要的内容 ： MID
 */
data class Video(
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
     * 视频当前分p的标识，通常为视频第一个分P的标识。
     * 即便是有多个分P，或者只有一个分p的视频
     */
    val cid: Long,
    /**
     * 视频的标题
     */
    val title: String,
    /**
     * 稿件总时长(所有分P) 单位为秒
     */
    val duration:Long,
    /**
     * 视频封面的URL
     */
    val cover: String,
    /**
     * 稿件时间记录
     */
    val time: VideoType.Time,
    /**
     * 视频的URL，通常为：https://www.bilibili.com/video/{BVID}
     */
    val url: String="https://www.bilibili.com/video/${bvid}",
    /**
     * 视频的CID列表
     * 视频有多少分p 就有多少CID，每一个分p 就对应一个唯一的CID。
     * 此字段在部分情况下都为null，因此想要获取不为null 的CID 列表请 使用方法: toCids()
     */
    var cids: List<Long>?=null,
    /**
     * 稿件简介
     */
    var describe: VideoType.Describe?=null,
    /**
     * 稿件类型，原创、转载
     */
    var type: VideoType.Type?=null,
    /**
     * 视频分P
     */
    var page: VideoType.PageEntrance?=null,

    /**
     * CC Subtitle
     */
    var CCSubtitle: VideoType.Subtitle?=null,

    /**
     * up们的ID
     */
    var authorMid:List<Long>?=null,
    /**
     * 视频状态：点赞、投币等
     */
    var stat: VideoType.Stat?=null
) {
    /**
     * 初始化这个视频单元，使他的属性补充完整不为null
     */
    fun init() {
        TODO()
    }

    /**
     * 获取视频所有的cid，该方法将等待
     */
    fun toCids(): List<Long>? {
        if (cids != null) return cids
        TODO("没写完")
        return cids
    }
}

/**
 * 与视频单元相关的字段，都是一个内部类
 */
class VideoType {

    /**
     * 稿件时间
     */
    data class Time(
        /**
         * 稿件公布日期 时间戳
         */
        val publishDate: Long,
        /**
         * 稿件上传日期 时间戳
         */
        var uploadDate: Long=publishDate
    )

    /**
     * 简介
     */
    data class Describe(
        /**
         * 简介
         */
        var describe: String,
        /**
         * 简介第二版
         */
        var describeV2: String?
    )

    /**
     * 稿件类型：转载、原创
     */
    data class Type(
        /**
         * 类型：
         * 1原创
         * 2转载
         */
        var type: Int,
    ) {
        /**
         * 是否是原创
         */
        fun isOriginal() = type == 1
    }

    /**
     * 视频分p管理
     */
    data class PageEntrance(
        /**
         * 视频page列表（无序）
         */
        val pages: List<Page>,
        /**
         * 视频有多少个page
         */
        val length: Int
    )

    /**
     * 分p
     */
    data class Page(
        /**
         * 当前page 的cid
         */
        val cid: Int,
        /**
         * 当前分P分辨率	,部分较老视频无分辨率值
         */
        var dimension: Dimension,
        /**
         * 当前分P持续时间:单位为秒
         */
        val duration: Int,
        /**
         * 视频来源:<br>
         * vupload：普通上传（B站）<br>
         * hunan：芒果TV<br>
         * qq：腾讯<br>
         */
        var from: String,
        /**
         * 当前page所在page列表中位置
         */
        val page: Int,
        /**
         * 当前分P标题
         */
        val subtitle: String,
        /**
         * 站外视频vid	仅站外视频有效
         */
        var vid: String?,
        var weblink: String
    )

    /**
     * 分辨率
     */
    data class Dimension(
        var height: Int,
        var rotate: Int,
        var width: Int
    )

    /**
     * 视频的CC弹幕
     */
    data class Subtitle(
        /**
         * 是否允许提交字幕
         */
        var allow_submit: Boolean,
        /**
         * 字幕列表
         */
        val list: List<CC>
    )

    data class CC(
        /**
         * 字幕id
         */
        var id:Long?,
        /**
         * 字幕语言
         */
        var lan:String?,
        /**
         * 字幕语言名称
         */
        var lanDoc:String?,
        /**
         * 是否锁定
         */
        var is_lock:Boolean?,
        /**
         * json格式字幕文件url
         */
        var url:String?,
        var type:Int?,
        /**
         * AI 类型
         */
        var aiType:Int?,
        /**
         * AI 状态
         */
        var aiStatus:Int?,
        /**
         * 字幕上传者mid
         */
        var author_mid:Long?,
    )

    data class Stat(
        /**
         * 播放数
         */
        var view: Int,
        /**
         * 获赞数
         */
        var like: Int,
        /**
         * 警告/争议提示信息
         */
        var argue_msg: String?=null,
        /**
         * 投币数
         */
        var coin: Int?=null,
        /**
         * 弹幕数
         */
        var danmaku: Int?=null,
        /**
         * 点踩数
         */
        var dislike: Int?=null,
        /**
         * 视频评分
         */
        var evaruation: String?=null,
        /**
         * 收藏数
         */
        var favorite: Int?=null,
        /**
         * 历史最高排行: 大于1000不显示
         */
        var hisRank: Int?=null,


        /**
         * 当前排名：大于1000不显示
         */
        var nowRank: Int?=null,
        /**
         * 评论数
         */
        var reply: Int?=null,
        /**
         * 分享数
         */
        var share: Int?=null
    )
}