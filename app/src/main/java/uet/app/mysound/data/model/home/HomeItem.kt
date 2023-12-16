package uet.app.mysound.data.model.home


import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class HomeItem(
    @SerializedName("contents")
    val contents: List<Content?>,
    @SerializedName("title")
    val title: String,
    val subtitle: String? = null,
    val thumbnail: List<Thumbnail>? = null,
    val channelId: String? = null,
)