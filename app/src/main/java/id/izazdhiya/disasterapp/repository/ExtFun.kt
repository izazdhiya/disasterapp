package id.izazdhiya.disasterapp.repository

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> ComponentActivity.viewModelsFactory(crossinline viewModelInitialization: () -> T): Lazy<T> {
    return viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInitialization.invoke() as T
            }
        }
    }
}


//inline fun <reified VM : ViewModel> ComponentActivity.viewModelsFactory(
//    noinline viewModelInitialization: () -> VM
//): Lazy<VM> {
//    return viewModels {
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return viewModelInitialization() as T
//            }
//        }
//    }
//}