package uet.app.youtubeExtractor.pages

import uet.app.youtubeExtractor.models.Album
import uet.app.youtubeExtractor.models.AlbumItem
import uet.app.youtubeExtractor.models.Artist
import uet.app.youtubeExtractor.models.MusicResponsiveListItemRenderer
import uet.app.youtubeExtractor.models.SongItem
import uet.app.youtubeExtractor.models.Thumbnails
import uet.app.youtubeExtractor.models.oddElements
import uet.app.youtubeExtractor.utils.parseTime

data class AlbumPage(
    val album: AlbumItem,
    val songs: List<SongItem>,
    val description: String?,
    val thumbnails: Thumbnails?,
    val duration: String?,
) {
    companion object {
        fun fromMusicResponsiveListItemRenderer(renderer: MusicResponsiveListItemRenderer?): SongItem? {
            if (renderer == null) return null
            else {
                return SongItem(
                    id = renderer.playlistItemData?.videoId ?: return null,
                    title = renderer.flexColumns.firstOrNull()
                        ?.musicResponsiveListItemFlexColumnRenderer?.text?.runs
                        ?.firstOrNull()?.text ?: return null,
                    artists = renderer.flexColumns.getOrNull(1)?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.oddElements()
                        ?.map {
                            Artist(
                                name = it.text,
                                id = it.navigationEndpoint?.browseEndpoint?.browseId
                            )
                        } ?: return null,
                    album = renderer.flexColumns.getOrNull(2)?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()
                        ?.let {
                            Album(
                                name = it.text,
                                id = it.navigationEndpoint?.browseEndpoint?.browseId!!
                            )
                        } ?: return null,
                    duration = renderer.fixedColumns?.firstOrNull()
                        ?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()
                        ?.text?.parseTime() ?: return null,
                    thumbnail = renderer.thumbnail?.musicThumbnailRenderer?.getThumbnailUrl()
                        ?: return null,
                    thumbnails = renderer.thumbnail.musicThumbnailRenderer.thumbnail,
                    explicit = renderer.badges?.find {
                        it.musicInlineBadgeRenderer.icon.iconType == "MUSIC_EXPLICIT_BADGE"
                    } != null
                )
            }
        }
    }
}