package com.example.newsapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("top-headlines?country=us&apiKey=537b32cbdf884272a5be9e5ac9f668fd")
    fun getTopNewsByCategory(@Query("category") category: String): Call<NewsResponse>
}
