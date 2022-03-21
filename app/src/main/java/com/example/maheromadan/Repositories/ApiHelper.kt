package com.example.maheromadan.Repositories

import com.example.maheromadan.interfaces.ApiService

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRamadanDetails(districtCode: String) {
        apiService.getRamadanDetails(url = districtCode)
    }
}