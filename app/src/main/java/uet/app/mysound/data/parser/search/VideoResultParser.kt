package uet.app.mysound.data.parser.search

import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.mysound.data.model.searchResult.songs.Thumbnail
import uet.app.mysound.data.model.searchResult.videos.VideosResult
import uet.app.youtubeExtractor.models.SongItem
import uet.app.youtubeExtractor.pages.SearchResult

fun parseSearchVideo(result: SearchResult): ArrayList<VideosResult> {
    val songsResult: ArrayList<VideosResult> = arrayListOf()
    result.items.forEach {
        val song = it as SongItem
        songsResult.add(
            VideosResult(
                artists = song.artists.map { artistItem ->
                    Artist(
                        id = artistItem.id,
                        name = artistItem.name
                    )
                },
                category = "Video",
                duration = if (song.duration != null) "%02d:%02d".format(song.duration!! / 60, song.duration!! % 60) else "",
                durationSeconds = song.duration ?: 0,
                resultType = "Video",
                thumbnails = listOf(Thumbnail(544, Regex("([wh])120").replace(song.thumbnail, "$1544"), 544)),
                title = song.title,
                videoId = song.id,
                videoType = "Video",
                views = null,
                year = ""
            )
        )
    }
    return songsResult
}