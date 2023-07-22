package id.izazdhiya.disasterapp.model.network.response


import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("disaster_type")
    val disasterType: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("partner_code")
    val partnerCode: Any,
    @SerializedName("partner_icon")
    val partnerIcon: Any,
    @SerializedName("pkey")
    val pkey: String,
    @SerializedName("report_data")
    val reportData: ReportData,
    @SerializedName("source")
    val source: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tags")
    val tags: Tags,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: Any,
    @SerializedName("url")
    val url: String
)