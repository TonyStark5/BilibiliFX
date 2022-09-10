package ink.bluecloud.model.pojo.video.info

class VideoInfoJsonRoot {

    data class Root(
        val code: Int,
        val `data`: Data,
        val message: String,
        val ttl: Int
    )

    data class Data(
        val aid: Long,
        val bvid: String,
        val cid: Long,
        val copyright: Int,
        val ctime: Long,//上传日期
        val desc: String,
        val desc_v2: List<DescV2>,
        val dimension: Dimension,
        val duration: Int,
        val `dynamic`: String,
        val honor_reply: HonorReply,
        val is_chargeable_season: Boolean,
        val is_season_display: Boolean,
        val is_story: Boolean,
        val like_icon: String,
        val mission_id: Int,
        val no_cache: Boolean,
        val owner: Owner,
        val pages: List<Page>,
        val pic: String,
        val premiere: Any,
        val pubdate: Long,
        val rights: Rights,
        val stat: Stat,
        val state: Int,
        val subtitle: Subtitle,
        val teenage_mode: Int,
        val tid: Int,
        val title: String,
        val tname: String,
        val user_garb: UserGarb,
        val videos: Int
    )

    data class DescV2(
        val biz_id: Int,
        val raw_text: String,
        val type: Int
    )

    data class Dimension(
        val height: Int,
        val rotate: Int,
        val width: Int
    )

    class HonorReply

    data class Owner(
        val face: String,
        val mid: Int,
        val name: String
    )

    data class Page(
        val cid: Int,
        val dimension: Dimension,
        val duration: Int,
        val from: String,
        val page: Int,
        val part: String,
        val vid: String,
        val weblink: String
    )

    data class Rights(
        val arc_pay: Int,
        val autoplay: Int,
        val bp: Int,
        val clean_mode: Int,
        val download: Int,
        val elec: Int,
        val free_watch: Int,
        val hd5: Int,
        val is_360: Int,
        val is_cooperation: Int,
        val is_stein_gate: Int,
        val movie: Int,
        val no_background: Int,
        val no_reprint: Int,
        val no_share: Int,
        val pay: Int,
        val ugc_pay: Int,
        val ugc_pay_preview: Int
    )

    data class Stat(
        val aid: Int,
        val argue_msg: String,
        val coin: Int,
        val danmaku: Int,
        val dislike: Int,
        val evaluation: String,
        val favorite: Int,
        val his_rank: Int,
        val like: Int,
        val now_rank: Int,
        val reply: Int,
        val share: Int,
        val view: Int
    )

    data class Subtitle(
        val allow_submit: Boolean,
        val list: List<CC>
    )


    data class CC(
        val id:Long?,
        val lan:String?,
        val lan_doc:String?,
        val is_lock:Boolean?,
        val subtitle_url:String?,
        val type:Int?,
        val id_str:String?,
        val ai_type:Int?,
        val ai_status:Int?,
        val author_mid:Long?,
    )

    data class UserGarb(
        val url_image_ani_cut: String
    )
}