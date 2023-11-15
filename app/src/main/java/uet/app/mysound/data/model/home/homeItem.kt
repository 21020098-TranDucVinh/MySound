package uet.app.mysound.data.model.home

import com.google.gson.annotations.SerializedName

data class homeItem(
    @SerializedName("contents")
    val contents: List<Content?>,
    @SerializedName("title")
    val title: String
)