package uet.app.mysound.data.model.explore.mood

import com.google.gson.annotations.SerializedName

data class MoodsMoment(
    @SerializedName("params")
    val params: String,
    @SerializedName("title")
    val title: String
)