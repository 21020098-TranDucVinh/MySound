package uet.app.mysound.data.model.explore.mood.genre


import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class Content(
    @SerializedName("playlistBrowseId")
    val playlistBrowseId: String,
    @SerializedName("thumbnail")
    val thumbnail: List<Thumbnail>?,
    @SerializedName("title")
    val title: Title
)