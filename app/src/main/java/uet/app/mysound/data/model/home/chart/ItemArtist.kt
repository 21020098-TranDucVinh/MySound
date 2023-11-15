package uet.app.mysound.data.model.home.chart

import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class ItemArtist(
    @SerializedName("browseId")
    val browseId: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("subscribers")
    val subscribers: String,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String,
    @SerializedName("trend")
    val trend: String
)