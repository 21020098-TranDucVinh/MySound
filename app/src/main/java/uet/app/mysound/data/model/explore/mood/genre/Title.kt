package uet.app.mysound.data.model.explore.mood.genre


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
)