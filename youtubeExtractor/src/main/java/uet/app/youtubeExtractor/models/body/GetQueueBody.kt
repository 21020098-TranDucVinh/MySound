package uet.app.youtubeExtractor.models.body

import uet.app.youtubeExtractor.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetQueueBody(
    val context: Context,
    val videoIds: List<String>?,
    val playlistId: String?,
)
