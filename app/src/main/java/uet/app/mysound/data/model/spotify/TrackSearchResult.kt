package uet.app.mysound.data.model.spotify


import com.google.gson.annotations.SerializedName

data class TrackSearchResult(
    @SerializedName("tracks")
    val tracks: Tracks?
)