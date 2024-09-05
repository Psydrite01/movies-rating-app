package com.example.movieratingapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//universal data source and json to gson converter
private val getMoviesData = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//get a specific movie with id
val getAMovieService = getMoviesData.create(apiService_moviebyid::class.java)

interface apiService_moviebyid{
    @GET("3/movie/{movieId}?api_key=59cb0544d19aa953457f3e2548d8236e")
    suspend fun dwnldAMovie(
        @Path("movieId")id:Int
    ):movies
}

//get images for a specific movie
val getMovieImages = getMoviesData.create(apiService_movieImage::class.java)

interface apiService_movieImage{
    @GET("3/movie/{movieId}/images?api_key=59cb0544d19aa953457f3e2548d8236e")
    suspend fun dwnldMovieImages(
        @Path("movieId")id:Int
    ):movieImageList
}

//get credits for a specific movie
val getMovieCredits = getMoviesData.create(apiService_moviecredits::class.java)

interface apiService_moviecredits{
    @GET("3/movie/{movieId}/credits?api_key=59cb0544d19aa953457f3e2548d8236e")
    suspend fun dwnldMovieCredits(
        @Path("movieId")id:Int
    ):movieCreditsList
}

//for popular movies
val popMoviesService = getMoviesData.create(apiService_pop::class.java)
interface apiService_pop{
    @GET("3/movie/popular?api_key=59cb0544d19aa953457f3e2548d8236e")
    suspend fun dwnldPopMovies(

    ):moviesList
}

//for upcoming movies
val upMoviesService = getMoviesData.create(apiService_up::class.java)

interface apiService_up{
    @GET("3/movie/upcoming?api_key=59cb0544d19aa953457f3e2548d8236e")
    suspend fun dwnldUpMovies(

    ):moviesList
}

//for action
val actionMoviesService = getMoviesData.create(apiService_action::class.java)
interface apiService_action{
    @GET("3/discover/movie?api_key=59cb0544d19aa953457f3e2548d8236e&with_genres=28")
    suspend fun dwnldActionMovies(

    ):moviesList
}

//for romance
val romanceMoviesService = getMoviesData.create(apiService_romance::class.java)
interface apiService_romance{
    @GET("3/discover/movie?api_key=59cb0544d19aa953457f3e2548d8236e&with_genres=10749")
    suspend fun dwnldRomanceMovies(

    ):moviesList
}


//upcoming:           https://api.themoviedb.org/3/movie/upcoming?api_key=59cb0544d19aa953457f3e2548d8236e
//action:             https://api.themoviedb.org/3/discover/movie?api_key=59cb0544d19aa953457f3e2548d8236e&with_genres=28
//Action: 28
//Adventure: 12
//Animation: 16
//Comedy: 35
//Crime: 80
//Documentary: 99
//Drama: 18
//Family: 10751
//Fantasy: 14
//History: 36
//Horror: 27
//Music: 10402
//Mystery: 9648
//Romance: 10749
//Science Fiction: 878
//TV Movie: 10770
//Thriller: 53
//War: 10752
//Western: 37