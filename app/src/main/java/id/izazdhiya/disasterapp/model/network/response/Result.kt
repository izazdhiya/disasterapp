package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("features")
    val features: List<Feature>,
    @SerializedName("type")
    val type: String
)