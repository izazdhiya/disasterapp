package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class ReportData(
    @SerializedName("accessabilityFailure")
    val accessabilityFailure: Int,
    @SerializedName("airQuality")
    val airQuality: Int,
    @SerializedName("condition")
    val condition: Int,
    @SerializedName("flood_depth")
    val floodDepth: Int,
    @SerializedName("report_type")
    val reportType: String,
    @SerializedName("structureFailure")
    val structureFailure: Int,
    @SerializedName("visibility")
    val visibility: Int
)