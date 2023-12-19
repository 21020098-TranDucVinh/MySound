package uet.app.mysound.data.parser

import uet.app.mysound.data.model.browse.album.AlbumBrowse
import uet.app.mysound.data.model.browse.album.Track
import uet.app.mysound.data.model.searchResult.songs.Album
import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.mysound.data.parser.MySound.parseMyAlbumBrowseJson
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

fun parseAlbumDataFromMySound(data: String): AlbumBrowse {

    val mySoundData = parseMyAlbumBrowseJson(data)

    val artistFromParser = mySoundData.artist // Assuming this is of type MySound.Artist

    val artistConverted = uet.app.mysound.data.model.searchResult.songs.Artist(
        id = artistFromParser.id.toString(),
        name = artistFromParser.name
    )

// Create a list and add the artistConverted object
    val artistList: MutableList<uet.app.mysound.data.model.searchResult.songs.Artist> =
        mutableListOf()
    artistList.add(artistConverted)

// Now, artistList contains the converted artist object


// Now, artistConverted is of type uet.app.mysound.data.model.searchResult.songs.Artist
    val listThumbnail = mutableListOf<uet.app.mysound.data.model.searchResult.songs.Thumbnail>()

    val thumbnail = uet.app.mysound.data.model.searchResult.songs.Thumbnail(
        height = 100,
        url = "https://scontent.fhan17-1.fna.fbcdn.net/v/t1.15752-9/370326255_716546323735823_6866928491541157783_n.png?_nc_cat=107&ccb=1-7&_nc_sid=8cd0a2&_nc_eui2=AeFhaPYDgUdUQDCqdsbmdsllLxgXX_7rUOUvGBdf_utQ5V5eXDAkUBUy4xaJKw6gyd77PLlAs3EL1GiUheZNrOr6&_nc_ohc=ffsDqzOvysIAX9pv0VJ&_nc_oc=AQnwRkvz5cRQkRgWH9352yhYF2BnJIb5XPlh6HLGgA5DpWxTNsXfA1nISOQOuufNGLo&_nc_ht=scontent.fhan17-1.fna&oh=03_AdRJbycGqIGldmAP6SavfEDZqp6rCwW3VIqK2ZoJ2cbHZQ&oe=65A8B232",
        width = 200
    )

    val thumbnail1 = uet.app.mysound.data.model.searchResult.songs.Thumbnail(
        height = 100,
        url = "https://scontent.fhan17-1.fna.fbcdn.net/v/t1.15752-9/370326255_716546323735823_6866928491541157783_n.png?_nc_cat=107&ccb=1-7&_nc_sid=8cd0a2&_nc_eui2=AeFhaPYDgUdUQDCqdsbmdsllLxgXX_7rUOUvGBdf_utQ5V5eXDAkUBUy4xaJKw6gyd77PLlAs3EL1GiUheZNrOr6&_nc_ohc=ffsDqzOvysIAX9pv0VJ&_nc_oc=AQnwRkvz5cRQkRgWH9352yhYF2BnJIb5XPlh6HLGgA5DpWxTNsXfA1nISOQOuufNGLo&_nc_ht=scontent.fhan17-1.fna&oh=03_AdRJbycGqIGldmAP6SavfEDZqp6rCwW3VIqK2ZoJ2cbHZQ&oe=65A8B232",
        width = 200
    )

    listThumbnail.add(thumbnail)
    listThumbnail.add(thumbnail1)
    val trackList: MutableList<uet.app.mysound.data.model.browse.album.Track> =
        mutableListOf()

    if (mySoundData.songs.isNotEmpty()) {
        for (song in mySoundData.songs) {
            val track = Track(
                album = Album(
                    id = mySoundData.id.toString(),
                    name = mySoundData.name,
                ),
                artists = listOf(
                    Artist(
                        id = song.artistId.toString(),
                        name = mySoundData.artist.name
                    )
                ),
                duration = song.length.toString(),
                durationSeconds = null,
                isAvailable = true,
                isExplicit = false,
                likeStatus = null,
                thumbnails = listThumbnail,
                title = song.title,
                videoId = song.id,
                videoType = null,
                category = null,
                feedbackTokens = null,
                resultType = null,
                year = song.year
            )
            trackList.add(track)
        }
    }

    return AlbumBrowse(
        artists = artistList,
        audioPlaylistId = "data",
        description = "unknown",
        duration = "20",
        durationSeconds = 50000,
        thumbnails = listThumbnail,
        title = mySoundData.name,
        trackCount = mySoundData.songs.size,
        tracks = trackList,
        type = "Album",
        year = "2023"
    )
}