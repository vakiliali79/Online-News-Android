package com.alivakili.news.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val baseUrl="https://newsapi.org/v2/"
    val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    object ApiClient {
        val apiService: Endpoint by lazy {
            retrofit.create(Endpoint::class.java)
        }
    }



}