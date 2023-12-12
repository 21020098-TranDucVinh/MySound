package uet.app.mysound.data.model


import com.google.gson.annotations.SerializedName

data class thumbnailUrl(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("thumbnails")
    val thumbnails: String
)