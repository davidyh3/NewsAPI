package com.example.newsapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsListViewModel : ViewModel() {

    // LiveData to hold the list of news articles
    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    init {
        viewModelScope.launch {
            // Initialize newsApiService inside the coroutine
            val newsApiService = RetrofitService.newsApiService

            fetchTopNews(newsApiService)?.let { articles ->
                // Update the newsList with the fetched articles
                // For example, you can add them to the existing list
                _newsList.value = articles
            }
        }
    }

    private suspend fun fetchTopNews(newsAPI: NewsAPI, category: String = ""): List<News>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = newsAPI.getTopNewsByCategory(category).execute()
                if (response.isSuccessful) {
                    response.body()?.articles
                } else {
                    // Handle unsuccessful response
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    // Function to fetch news by category
    fun fetchNewsByCategory(category: String) {
        viewModelScope.launch {
            // Handle the result of getTopHeadlines
            val newsApiService = RetrofitService.newsApiService

            fetchTopNews(newsApiService, category)?.let { articles ->
                _newsList.value = articles
            }
        }
    }
}