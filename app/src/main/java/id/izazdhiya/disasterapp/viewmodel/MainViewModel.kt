package id.izazdhiya.disasterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import id.izazdhiya.disasterapp.datastore.SettingsDataStore

class MainViewModel(private val preferences: SettingsDataStore) : ViewModel() {
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    class Factory(private val preferences: SettingsDataStore) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(preferences) as T
    }
}