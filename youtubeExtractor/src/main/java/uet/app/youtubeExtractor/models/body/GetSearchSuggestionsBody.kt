package uet.app.youtubeExtractor.models.body

import uet.app.youtubeExtractor.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetSearchSuggestionsBody(
    val context: Context,
    val input: String,
)
