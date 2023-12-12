package uet.app.youtubeExtractor.models.body

import uet.app.youtubeExtractor.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetTranscriptBody(
    val context: Context,
    val params: String,
)
