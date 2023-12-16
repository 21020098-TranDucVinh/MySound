package uet.app.youtubeExtractor.pages

import uet.app.youtubeExtractor.models.SongItem

data class PlaylistContinuationPage(
    val songs: List<SongItem>,
    val continuation: String?,
)
