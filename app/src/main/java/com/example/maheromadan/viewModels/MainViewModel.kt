package com.example.maheromadan.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.maheromadan.Repositories.MainRepository
import com.example.maheromadan.interfaces.ApiService
import com.example.maheromadan.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel (private val mainRepository: ApiService) : ViewModel() {

    fun getRamadanDetails(districtCode:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getRamadanDetails("json_resp.php?dist=$districtCode")))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getDayDetails(districtCode:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val currentDate = sdf.format(Date())
            emit(Resource.success(data = mainRepository.getDayDetails("$currentDate?address=${districtCode.toUpperCase()},ASIA")))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}