package com.jayesh.bookingapp

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<Movie>,
)

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?
)


