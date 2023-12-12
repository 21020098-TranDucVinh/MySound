package uet.app.mysound.data.model.mediaService

import android.graphics.Bitmap
import uet.app.mysound.data.model.searchResult.songs.Album
import uet.app.mysound.data.model.searchResult.songs.Artist
import uet.app.mysound.data.model.searchResult.songs.Thumbnail

data class Song (
    val title: String?,
    val artists: List<Artist>?,
    val duration: Long,
    val lyrics: Any,
    val album: Album,
    val videoId: String,
    val thumbnail: Thumbnail?,
    val thumbnailBitmap: Bitmap?,
    val isLocal: Boolean
)