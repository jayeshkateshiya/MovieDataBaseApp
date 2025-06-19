package com.jayesh.bookingapp

import com.google.gson.annotations.SerializedName

class PopularMovieResponse(
    val results: List<PopulateMovie>
)
data class PopulateMovie(
    val id: Int,
    val original_title: String,
    val release_date: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?
)