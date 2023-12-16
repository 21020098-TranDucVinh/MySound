package uet.app.youtubeExtractor.pages

import uet.app.youtubeExtractor.models.AlbumItem

data class ExplorePage(
    val newReleaseAlbums: List<AlbumItem>,
    val moodAndGenres: List<MoodAndGenres.Item>,
)
