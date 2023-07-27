package id.izazdhiya.disasterapp.model.network.response.flood


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("arcs")
    val arcs: List<List<List<Int>>>,
    @SerializedName("bbox")
    val bbox: List<Double>,
    @SerializedName("objects")
    val objects: Objects,
    @SerializedName("transform")
    val transform: Transform,
    @SerializedName("type")
    val type: String
)