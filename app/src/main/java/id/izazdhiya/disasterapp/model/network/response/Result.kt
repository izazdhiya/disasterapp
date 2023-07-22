package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("arcs")
    val arcs: List<Any>,
    @SerializedName("bbox")
    val bbox: List<Double>,
    @SerializedName("objects")
    val objects: Objects,
    @SerializedName("type")
    val type: String
)