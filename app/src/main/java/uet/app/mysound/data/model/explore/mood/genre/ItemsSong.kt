package uet.app.mysound.data.model.explore.mood.genre

import uet.app.mysound.data.model.searchResult.songs.Artist

data class ItemsSong(
    val title: String,
    val artist: List<Artist>?,
    val videoId: String,
)
