package com.jayesh.bookingapp


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Movie.route) { MovieScreen() }
        composable(BottomNavItem.TvShows.route) { TvShowsScreen() }
        composable(BottomNavItem.People.route) { PeopleScreen() }
    }
}
