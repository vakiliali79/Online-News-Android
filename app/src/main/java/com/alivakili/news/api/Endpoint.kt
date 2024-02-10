package com.alivakili.news.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("top-headlines")
    fun topHeadlines(
        @Query("apiKey")apikey:String,
        @Query("language")language: String,
        @Query("country")country:String,
        @Query("category")category:String,
        ): Call<NewsAnswerDTO>
}
