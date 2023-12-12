package uet.app.mysound.data.parser.search

import uet.app.mysound.data.model.searchResult.artists.ArtistsResult
import uet.app.mysound.data.model.searchResult.songs.Thumbnail
import uet.app.youtubeExtractor.models.ArtistItem
import uet.app.youtubeExtractor.pages.SearchResult

fun parseSearchArtist(result: SearchResult): ArrayList<ArtistsResult>{
    val artistsResult: ArrayList<ArtistsResult> = arrayListOf()
    result.items.forEach {
        val artist = it as ArtistItem
        artistsResult.add(
            ArtistsResult(
                artist = artist.title,
                browseId = artist.id,
                category = "Artist",
                radioId = artist.radioEndpoint?.playlistId ?: "",
                resultType = "Artist",
                shuffleId = artist.shuffleEndpoint?.playlistId ?: "",
                thumbnails = listOf(Thumbnail(544, Regex("([wh])120").replace(artist.thumbnail, "$1544"), 544))
            )
        )
    }
    return artistsResult
}