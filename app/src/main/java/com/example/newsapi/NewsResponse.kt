package com.example.newsapi

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)
