package uet.app.mysound.data.parser

import uet.app.mysound.data.model.browse.album.AlbumBrowse
import uet.app.mysound.data.model.browse.album.Track
import uet.app.mysound.data.model.searchResult.songs.Album
import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.youtubeExtractor.pages.AlbumPage

fun parseAlbumData(data: AlbumPage): AlbumBrowse {
    val artist: ArrayList<Artist> = arrayListOf()
    data.album.artists?.forEach {
        artist.add(Artist(it.id, it.name))
    }
    val songs: ArrayList<Track> = arrayListOf()
    data.songs.forEach { songItem ->
        songs.add(
            Track(
                album = Album(
                    id = data.album.id,
                    name = data.album.title
                ),
                artists = songItem.artists.map { artistItem ->
                    Artist(
                        id = artistItem.id,
                        name = artistItem.name
                    )
                },
                duration = if (songItem.duration != null) "%02d:%02d".format(songItem.duration!! / 60, songItem.duration!! % 60) else "",
                durationSeconds = songItem.duration ?: 0,
                isAvailable = false,
                isExplicit = songItem.explicit,
                likeStatus = "INDIFFERENT",
                thumbnails = songItem.thumbnails?.thumbnails?.toListThumbnail() ?: listOf(),
                title = songItem.title,
                videoId = songItem.id,
                videoType = "Video",
                category = null,
                feedbackTokens = null,
                resultType = null,
                year = data.album.year.toString()
            )
        )
    }

    return AlbumBrowse(
        artists = artist,
        audioPlaylistId = data.album.playlistId,
        description = data.description ?: "",
        duration = data.duration ?: "",
        durationSeconds = 0,
        thumbnails = data.thumbnails?.thumbnails?.toListThumbnail() ?: listOf(),
        title = data.album.title,
        trackCount = songs.size,
        tracks = songs,
        type = "Album",
        year = data.album.year.toString()
    )
}