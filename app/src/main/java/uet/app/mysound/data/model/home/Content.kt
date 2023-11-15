package uet.app.mysound.data.model.home

import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.model.searchResult.songs.Album
import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class Content(
    @SerializedName("album")
    val album: Album?,
    @SerializedName("artists")
    val artists: List<Artist>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("isExplicit")
    val isExplicit: Boolean?,
    @SerializedName("playlistId")
    val playlistId: String?,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String,
    @SerializedName("videoId")
    val videoId: String?,
    @SerializedName("views")
    val views: String?
)