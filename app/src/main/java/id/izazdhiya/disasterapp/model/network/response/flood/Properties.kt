package id.izazdhiya.disasterapp.model.network.response.flood


import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("area_id")
    val areaId: String,
    @SerializedName("area_name")
    val areaName: String,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("geom_id")
    val geomId: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("parent_name")
    val parentName: String,
    @SerializedName("state")
    val state: Int
)