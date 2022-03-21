package com.example.maheromadan.Repositories

import com.example.maheromadan.interfaces.ApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {



    private fun getRetrofit(whichApi:String): Retrofit {
        if (whichApi.equals("dist")){
            val okHttpClient: OkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
            return Retrofit.Builder()
                .baseUrl("https://sbitsbd.com/ramadanApp/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }else{
            //val okHttpClient: OkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder()
                .baseUrl("https://api.aladhan.com/v1/timingsByAddress/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        //Doesn't require the adapter
    }


    public fun apiServic(whichApi:String):ApiService{
        return getRetrofit(whichApi ).create(ApiService::class.java)
    }
   // val apiService: ApiService = getRetrofit( ).create(ApiService::class.java)


}