package com.example.maheromadan.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.maheromadan.Repositories.ApiHelper
import com.example.maheromadan.Repositories.MainRepository
import com.example.maheromadan.interfaces.ApiService

class ViewModelFactory(private val apiHelper: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}