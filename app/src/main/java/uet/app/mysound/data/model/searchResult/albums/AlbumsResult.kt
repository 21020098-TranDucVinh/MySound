package uet.app.mysound.data.model.searchResult.albums


import com.google.gson.annotations.SerializedName
import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class AlbumsResult(
    @SerializedName("artists")
    val artists: List<Artist>,
    @SerializedName("browseId")
    val browseId: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("duration")
    val duration: Any,
    @SerializedName("isExplicit")
    val isExplicit: Boolean,
    @SerializedName("resultType")
    val resultType: String,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: String
)