package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class PersonLocation(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)