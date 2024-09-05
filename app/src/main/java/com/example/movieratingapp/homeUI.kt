package com.example.movieratingapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun homePage(emailName: String,goToLoginPage:()->Unit, goToShowMovie:(Int)->Unit, modifier: Modifier= Modifier){
    val popMoviesViewModel: mainViewModel = viewModel()
    val popMoviesViewState by popMoviesViewModel.moviesDataLoadingState_pop
    
    val upMoviesViewModel: mainViewModel = viewModel()
    val upMoviesViewState by upMoviesViewModel.moviesDataLoadingState_up

    val actionMoviesViewModel: mainViewModel = viewModel()
    val actionMoviesViewState by actionMoviesViewModel.moviesDataLoadingState_action

    val romanceMoviesViewModel: mainViewModel = viewModel()
    val romanceMoviesViewState by romanceMoviesViewModel.moviesDataLoadingState_romance

    Box(
        modifier = Modifier
    ){
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(200.dp))

        when{
            popMoviesViewState.isloading
                    || upMoviesViewState.isloading
                    || actionMoviesViewState.isloading
                    || romanceMoviesViewState.isloading->{
                Text(text = "loading")
            }
            popMoviesViewState.errorGettingMovies != null -> {
                Text(text = "error: ${popMoviesViewState.errorGettingMovies}")
            }
            upMoviesViewState.errorGettingMovies != null -> {
                Text(text = "error: ${upMoviesViewState.errorGettingMovies}")
            }
            actionMoviesViewState.errorGettingMovies != null -> {
                Text(text = "error: ${actionMoviesViewState.errorGettingMovies}")
            }
            romanceMoviesViewState.errorGettingMovies != null -> {
                Text(text = "error: ${romanceMoviesViewState.errorGettingMovies}")
            }
            else ->{
                moviesHomeScreen(
                    goToParentPage = goToLoginPage,
                    data_pop = popMoviesViewState.list,
                    data_up = upMoviesViewState.list,
                    data_action = actionMoviesViewState.list,
                    data_romance = romanceMoviesViewState.list,
                    email_name = emailName,
                    goToShowMovie = goToShowMovie)
            }
        }
    }
}

@SuppressLint("Range")
@Composable
fun moviesHomeScreen(
    email_name: String,
    goToParentPage:()->Unit,
    goToShowMovie: (Int) -> Unit,
    data_pop: List<movies>,
    data_up: List<movies>,
    data_action: List<movies>,
    data_romance: List<movies>){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(top = 50.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ){

            Row () {
                Button(onClick = {
                    goToParentPage()
                }) {
                    Text(text = "...")
                }
                Text(text = "Discover Movies ${email_name}")
            }
            Divider(
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth(1.5f)
                    .padding(top = 20.dp)
            )
            Text(text = "Popular Now")
            LazyRow {
                items(data_pop){
                    showMoviePreview(movieData = it, goToMovie = goToShowMovie)
                }
            }

            Text(text = "Upcoming")
            LazyRow {
                items(data_up){
                    showMoviePreview(movieData = it, goToMovie = goToShowMovie)
                }
            }

            Text(text = "Action")
            LazyRow {
                items(data_action){
                    showMoviePreview(movieData = it, goToMovie = goToShowMovie)
                }
            }
            Text(text = "Romance")
            LazyRow {
                items(data_romance){
                    showMoviePreview(movieData = it, goToMovie = goToShowMovie)
                }
            }
        }
    }
}

@Composable
fun showMoviePreview(movieData: movies, goToMovie: (Int) -> Unit){
    var posterPath:String="https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movieData.poster_path
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .clickable { goToMovie(movieData.id) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(posterPath),
            contentDescription = "poster",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.8f))
        Text(text = "${movieData.id}")

    }
}