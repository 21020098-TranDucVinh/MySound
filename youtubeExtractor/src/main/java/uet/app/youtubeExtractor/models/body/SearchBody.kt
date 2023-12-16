package uet.app.youtubeExtractor.models.body

import uet.app.youtubeExtractor.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class SearchBody(
    val context: Context,
    val query: String?,
    val params: String?,
)
