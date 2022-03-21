package com.example.maheromadan.interfaces

import com.example.maheromadan.model.RamadanDetails
import com.example.maheromadan.model.SalatTimingClass
import retrofit2.http.GET
import retrofit2.http.Url

interface   ApiService {

    @GET()
    suspend fun getRamadanDetails(@Url url:String): RamadanDetails

    @GET
    suspend fun getDayDetails(@Url url:String): SalatTimingClass
}