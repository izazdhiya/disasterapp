package id.izazdhiya.disasterapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import id.izazdhiya.disasterapp.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DisasterRepository(private val apiService: ApiService) {
    suspend fun getReports(geoFormat: String) = apiService.getReports(geoFormat)
    suspend fun getReportsByProvince(geoFormat: String, provinceId: String) = apiService.getReportsByProvince(geoFormat, provinceId)
    suspend fun getReportsByDisaster(geoFormat: String, disaster: String) = apiService.getReportsByDisaster(geoFormat, disaster)
    suspend fun getArchive(geoFormat: String, start: String, end: String) = apiService.getArchive(geoFormat, start, end)
    suspend fun getArchiveByProvince(geoFormat: String, start: String, end: String, provinceId: String) = apiService.getArchiveByProvince(geoFormat, start, end, provinceId)
    suspend fun getFloods(geoFormat: String, provinceId: String, state: Int) = apiService.getFloods(geoFormat, provinceId, state)
}