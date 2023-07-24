package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class DisasterReport(
    @SerializedName("result")
    val result: Result?,
    @SerializedName("statusCode")
    val statusCode: Int
)
