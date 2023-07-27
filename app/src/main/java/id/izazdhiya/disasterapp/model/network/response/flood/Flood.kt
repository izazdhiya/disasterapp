package id.izazdhiya.disasterapp.model.network.response.flood


import com.google.gson.annotations.SerializedName

data class Flood(
    @SerializedName("result")
    val result: Result,
    @SerializedName("statusCode")
    val statusCode: Int
)