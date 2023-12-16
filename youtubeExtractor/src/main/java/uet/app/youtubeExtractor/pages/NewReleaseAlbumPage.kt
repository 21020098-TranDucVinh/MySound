package uet.app.youtubeExtractor.pages

import uet.app.youtubeExtractor.models.AlbumItem
import uet.app.youtubeExtractor.models.Artist
import uet.app.youtubeExtractor.models.MusicTwoRowItemRenderer
import uet.app.youtubeExtractor.models.oddElements
import uet.app.youtubeExtractor.models.splitBySeparator

object NewReleaseAlbumPage {
    fun fromMusicTwoRowItemRenderer(renderer: MusicTwoRowItemRenderer): AlbumItem? {
        return AlbumItem(
            browseId = renderer.navigationEndpoint.browseEndpoint?.browseId ?: return null,
            playlistId = renderer.thumbnailOverlay
                ?.musicItemThumbnailOverlayRenderer?.content
                ?.musicPlayButtonRenderer?.playNavigationEndpoint
                ?.watchPlaylistEndpoint?.playlistId ?: return null,
            title = renderer.title.runs?.firstOrNull()?.text ?: return null,
            artists = renderer.subtitle?.runs?.splitBySeparator()?.getOrNull(1)?.oddElements()?.map {
                Artist(
                    name = it.text,
                    id = it.navigationEndpoint?.browseEndpoint?.browseId
                )
            } ?: return null,
            year = renderer.subtitle.runs.lastOrNull()?.text?.toIntOrNull(),
            thumbnail = renderer.thumbnailRenderer.musicThumbnailRenderer?.getThumbnailUrl() ?: return null,
            explicit = renderer.subtitleBadges?.find {
                it.musicInlineBadgeRenderer.icon.iconType == "MUSIC_EXPLICIT_BADGE"
            } != null
        )
    }
}
