package uet.app.youtubeExtractor.models.youtube


import uet.app.youtubeExtractor.models.Thumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    @SerialName("thumbnails")
    val thumbnails: List<Thumbnail>? = null
)