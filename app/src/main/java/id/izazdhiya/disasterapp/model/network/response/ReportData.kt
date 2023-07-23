package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class ReportData(
    @SerializedName("accessabilityFailure")
    val accessabilityFailure: Int,
    @SerializedName("airQuality")
    val airQuality: Int,
    @SerializedName("condition")
    val condition: Int,
    @SerializedName("evacuationArea")
    val evacuationArea: Boolean,
    @SerializedName("evacuationNumber")
    val evacuationNumber: Int,
    @SerializedName("fireDistance")
    val fireDistance: Double,
    @SerializedName("fireLocation")
    val fireLocation: FireLocation,
    @SerializedName("fireRadius")
    val fireRadius: FireRadius,
    @SerializedName("flood_depth")
    val floodDepth: Int,
    @SerializedName("impact")
    val impact: Int,
    @SerializedName("personLocation")
    val personLocation: PersonLocation,
    @SerializedName("report_type")
    val reportType: String,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("volcanicSigns")
    val volcanicSigns: List<Int>
)