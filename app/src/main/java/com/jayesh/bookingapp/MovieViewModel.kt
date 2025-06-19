package com.jayesh.bookingapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    val movies = mutableStateListOf<Movie>()
    var isLoading by mutableStateOf(false)
        private set

    var isInitialLoad by mutableStateOf(true)
        private set

    private var currentPage = 1
    private var isLastPage = false

    fun loadMoreMovies() {
        if (isLoading || isLastPage) return

        isLoading = true

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getMovies(
                    apiKey = "58df40f4b23a90469f35ddc32015465c",
                    page = currentPage
                )

                if (response.results.isNotEmpty()) {
                    movies.addAll(response.results)
                    currentPage++
                } else {
                    isLastPage = true
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error: ${e.localizedMessage}")
            } finally {
                isLoading = false
                isInitialLoad = false
            }
        }
    }

    init {
        loadMoreMovies()
    }
}




