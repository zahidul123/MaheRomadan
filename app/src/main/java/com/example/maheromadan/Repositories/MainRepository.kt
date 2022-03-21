package com.example.maheromadan.Repositories

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getRamadanDetails(districtCode:String) = apiHelper.getRamadanDetails(districtCode)

}