package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("coordinates")
    val coordinates: List<Double>,
    @SerializedName("properties")
    val properties: Properties,
    @SerializedName("type")
    val type: String
)