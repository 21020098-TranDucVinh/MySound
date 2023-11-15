package uet.app.mysound.data.model.browse.artist


import com.google.gson.annotations.SerializedName

data class Related(
    @SerializedName("browseId")
    val browseId: Any,
    @SerializedName("results")
    val results: List<ResultRelated>
)