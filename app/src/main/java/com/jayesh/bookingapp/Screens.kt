package com.jayesh.bookingapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen() {
    val viewModel: MovieViewModel = viewModel()
    val isLoading = viewModel.isLoading
    val isInitialLoad = viewModel.isInitialLoad
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Movies", "TV Shows")
    val movies = viewModel.movies

    val populateMovie: PopularMovieModelView = viewModel()

    val popularMovies = populateMovie.popularMovies
    val isLoadingPopular = populateMovie.isLoadingPopular
    val isInitialPopularLoad = populateMovie.isInitialPopularLoad

    if (isInitialLoad) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return // Don't draw rest of UI
    }

    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            title,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            ),
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (selectedTabIndex) {
            0 -> HorizontalMovieList("Movie List", movies, isLoading) {
                viewModel.loadMoreMovies()
            }

            1 -> HorizontalMovieList("TV Show List", movies, isLoading) {
                viewModel.loadMoreMovies()
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        PopularHorizontalMovieList("Popular", popularMovies, isLoadingPopular) {
            populateMovie.loadPopularMovies()
        }
    }

}


@Composable
fun MovieScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Movie Screen")
    }
}

@Composable
fun TvShowsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("TV Shows Screen")
    }
}

@Composable
fun PeopleScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("People Screen")
    }
}
