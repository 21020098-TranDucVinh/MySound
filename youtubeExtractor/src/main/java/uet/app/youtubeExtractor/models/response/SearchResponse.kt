package uet.app.youtubeExtractor.models.response

import uet.app.youtubeExtractor.models.Continuation
import uet.app.youtubeExtractor.models.MusicResponsiveListItemRenderer
import uet.app.youtubeExtractor.models.MusicShelfRenderer
import uet.app.youtubeExtractor.models.Tabs
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val contents: Contents?,
    val continuationContents: ContinuationContents?,
) {
    @Serializable
    data class Contents(
        val tabbedSearchResultsRenderer: Tabs?,
    )

    @Serializable
    data class ContinuationContents(
        val musicShelfContinuation: MusicShelfContinuation,
    ) {
        @Serializable
        data class MusicShelfContinuation(
            val contents: List<Content>,
            val continuations: List<Continuation>?,
        ) {
            @Serializable
            data class Content(
                val musicResponsiveListItemRenderer: MusicResponsiveListItemRenderer?,
                val musicMultiRowListItemRenderer: MusicShelfRenderer.Content.MusicMultiRowListItemRenderer?,
            )
        }
    }
}
