package uet.app.youtubeExtractor.models

sealed class MediaType {
    data object Song : MediaType()
    data object Video : MediaType()
}