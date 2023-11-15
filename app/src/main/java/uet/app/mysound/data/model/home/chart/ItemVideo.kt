package uet.app.mysound.data.model.home.chart

import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class ItemVideo(
    @SerializedName("artists")
    val artists: List<Artist>?,
    @SerializedName("playlistId")
    val playlistId: String,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String,
    @SerializedName("videoId")
    val videoId: String,
    @SerializedName("views")
    val views: String
)