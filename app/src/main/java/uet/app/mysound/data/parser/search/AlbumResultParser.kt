package uet.app.mysound.data.parser.search

import uet.app.mysound.data.model.searchResult.albums.AlbumsResult
import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.mysound.data.model.searchResult.songs.Thumbnail
import uet.app.youtubeExtractor.models.AlbumItem
import uet.app.youtubeExtractor.pages.SearchResult

fun parseSearchAlbum(result: SearchResult): ArrayList<AlbumsResult> {
    val albumsResult: ArrayList<AlbumsResult> = arrayListOf()
    result.items.forEach {
        val album = it as AlbumItem
        albumsResult.add(
            AlbumsResult(
                artists = album.artists?.map { artistItem ->
                    Artist(
                        id = artistItem.id,
                        name = artistItem.name
                    ) } ?: listOf(),
                browseId = album.browseId,
                category = "Album",
                duration = "",
                isExplicit = false,
                resultType = "Album",
                thumbnails = listOf(Thumbnail(544, Regex("([wh])120").replace(album.thumbnail, "$1544"), 544)),
                title = album.title,
                type = "Album",
                year = album.year.toString()
            )
        )
    }
    return albumsResult
}