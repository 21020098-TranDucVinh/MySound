package uet.app.mysound.data.model.home.chart


import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("items")
    val itemArtists: List<ItemArtist>,
    @SerializedName("playlist")
    val playlist: Any
)