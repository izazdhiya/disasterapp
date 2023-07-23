package id.izazdhiya.disasterapp.viewmodel

import androidx.lifecycle.liveData
import id.izazdhiya.disasterapp.model.network.Resource
import id.izazdhiya.disasterapp.repository.DisasterRepository
import kotlinx.coroutines.Dispatchers
import kotlin.Exception

class DisasterViewModel(private val repository: DisasterRepository) {

    fun getReports() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getReports("geojson")))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getReportsByProvince(provinceId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getReportsByProvince("geojson", provinceId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getReportsByDisaster(disaster: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getReportsByDisaster("geojson", disaster)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getArchive(start: String, end: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getArchive("geojson", start, end)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

    fun getArchiveByProvince(start: String, end: String, provinceId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(repository.getArchiveByProvince("geojson", start, end, provinceId)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}