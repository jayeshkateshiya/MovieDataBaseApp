package com.jayesh.bookingapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PopularMovieModelView : ViewModel() {
    val popularMovies = mutableStateListOf<PopulateMovie>()
    var isLoadingPopular by mutableStateOf(false)
        private set
    var isInitialPopularLoad by mutableStateOf(true)
        private set

    private var popularPage = 1
    private var isLastPopularPage = false

    fun loadPopularMovies() {
        if (isLoadingPopular || isLastPopularPage) return

        isLoadingPopular = true

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getPopularMovies(
                    apiKey = "58df40f4b23a90469f35ddc32015465c",
                    language = "en-US",
                    page = popularPage
                )

                if (response.results.isNotEmpty()) {
                    popularMovies.addAll(response.results.reversed())
                    popularPage++
                } else {
                    isLastPopularPage = true
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Popular Error: ${e.localizedMessage}")
            } finally {
                isLoadingPopular = false
                isInitialPopularLoad = false
            }
        }
    }

    init {
        loadPopularMovies() // optional initial call
    }
}