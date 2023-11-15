package uet.app.mysound.data.model.browse.artist


import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class ResultAlbum(
    @SerializedName("browseId")
    val browseId: String,
    @SerializedName("isExplicit")
    val isExplicit: Boolean,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)