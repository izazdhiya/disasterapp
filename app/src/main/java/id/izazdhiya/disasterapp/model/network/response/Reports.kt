package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class Reports(
    @SerializedName("statusCode")
    val statusCode: Int
)