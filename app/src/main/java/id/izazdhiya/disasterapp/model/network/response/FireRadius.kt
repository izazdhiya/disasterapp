package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class FireRadius(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)