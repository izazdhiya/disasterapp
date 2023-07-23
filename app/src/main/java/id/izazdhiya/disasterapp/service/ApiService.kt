package id.izazdhiya.disasterapp.service

import id.izazdhiya.disasterapp.model.network.response.DisasterReport
import id.izazdhiya.disasterapp.model.network.response.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reports")
    suspend fun getReports(
        @Query("geoformat") geoformat: String = "geojson"
    ): Result

    @GET("reports")
    suspend fun getReportsByProvince(
        @Query("geoformat") geoformat: String = "geojson",
        @Query("admin") provinceId: String
    ): Result

    @GET("reports")
    suspend fun getReportsByDisaster(
        @Query("geoformat") geoformat: String = "geojson",
        @Query("disaster") disasterType: String
    ): Result

    @GET("reports/archive")
    suspend fun getArchive(
        @Query("geoformat") geoformat: String = "geojson",
        @Query("start") startDate: String,
        @Query("end") endDate: String,
    ): Result

    @GET("reports/archive")
    suspend fun getArchiveByProvince(
        @Query("geoformat") geoformat: String = "geojson",
        @Query("start") startDate: String,
        @Query("end") endDate: String,
        @Query("admin") provinceId: String
    ): Result
}