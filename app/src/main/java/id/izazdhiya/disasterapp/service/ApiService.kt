package id.izazdhiya.disasterapp.service

import id.izazdhiya.disasterapp.model.network.response.DisasterReport
import id.izazdhiya.disasterapp.model.network.response.Result
import id.izazdhiya.disasterapp.model.network.response.flood.Flood
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reports")
    suspend fun getReports(
        @Query("geoformat") geoFormat: String,
        @Query("timeperiod") timePeriod: Int

    ): DisasterReport

    @GET("reports")
    suspend fun getReportsByProvince(
        @Query("geoformat") geoFormat: String,
        @Query("admin") provinceId: String,
        @Query("timeperiod") timePeriod: Int
    ): DisasterReport

    @GET("reports")
    suspend fun getReportsByDisaster(
        @Query("geoformat") geoFormat: String,
        @Query("disaster") disasterType: String,
        @Query("timeperiod") timePeriod: Int
    ): DisasterReport

    @GET("reports/archive")
    suspend fun getArchive(
        @Query("geoformat") geoFormat: String,
        @Query("start", encoded = true) startDate: String,
        @Query("end", encoded = true) endDate: String,
    ): DisasterReport

    @GET("reports/archive")
    suspend fun getArchiveByProvince(
        @Query("geoformat") geoFormat: String,
        @Query("start", encoded = true) startDate: String,
        @Query("end", encoded = true) endDate: String,
        @Query("admin") provinceId: String
    ): DisasterReport

    @GET("floods")
    suspend fun getFloods(
        @Query("geoformat") geoFormat: String,
        @Query("admin") provinceId: String,
        @Query("minimum_state") state: Int
    ) : Flood
}