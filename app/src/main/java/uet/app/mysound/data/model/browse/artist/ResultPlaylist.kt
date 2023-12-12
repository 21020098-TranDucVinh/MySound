package uet.app.mysound.data.model.browse.artist

import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class ResultPlaylist(
    val id: String,
    val author: String,
    val thumbnails: List<Thumbnail>,
    val title: String,
) {
}