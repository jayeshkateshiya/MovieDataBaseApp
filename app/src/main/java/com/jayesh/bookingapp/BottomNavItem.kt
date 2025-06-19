package com.jayesh.bookingapp


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

sealed class BottomNavItem(val route: String, val label: String, val icon: Int) {
    object Home : BottomNavItem("home", "Home", R.drawable.ic_home)
    object Movie : BottomNavItem("movie", "Movies", R.drawable.ic_movie)
    object TvShows : BottomNavItem("tvshows", "TV Shows", R.drawable.ic_tv)
    object People : BottomNavItem("people", "People", R.drawable.ic_pepole)
}
