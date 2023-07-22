package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class Output(
    @SerializedName("geometries")
    val geometries: List<Geometry>,
    @SerializedName("type")
    val type: String
)