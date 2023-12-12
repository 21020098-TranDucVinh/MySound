package uet.app.youtubeExtractor.models.body

import kotlinx.serialization.Serializable

@Serializable
data class FormData (
    val selectedValues: List<String> = listOf("ZZ")
)