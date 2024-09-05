package com.example.movieratingapp


import androidx.compose.runtime.IntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class mainViewModel :ViewModel() {
    private var _reqMovieLoadingState = mutableStateOf(movieLState())
    val reqMovieLoadingState: State<movieLState> = _reqMovieLoadingState

    private var _reqImageLoadingState = mutableStateOf(imagesLState())
    val reqImageLoadingState: State<imagesLState> = _reqImageLoadingState

    private var _reqCreditsLoadingState = mutableStateOf(creditsLState())
    val reqCreditsLoadingState: State<creditsLState> = _reqCreditsLoadingState

    private var _moviesDataLoadingState_pop = mutableStateOf(moviesLoadingState())
    val moviesDataLoadingState_pop: State<moviesLoadingState> = _moviesDataLoadingState_pop

    private var _moviesDataLoadingState_up = mutableStateOf(moviesLoadingState())
    val moviesDataLoadingState_up: State<moviesLoadingState> = _moviesDataLoadingState_up

    private var _moviesDataLoadingState_action = mutableStateOf(moviesLoadingState())
    val moviesDataLoadingState_action: State<moviesLoadingState> = _moviesDataLoadingState_action

    private var _moviesDataLoadingState_romance = mutableStateOf(moviesLoadingState())
    val moviesDataLoadingState_romance: State<moviesLoadingState> = _moviesDataLoadingState_romance

    init {
        fetchMovies()
    }

    fun copyChanges(x: MutableState<moviesLoadingState>, y: moviesList) {
        x.value = x.value.copy(
            list = y.results ?: emptyList(),
            errorGettingMovies = null,
            isloading = false
        )
        if (y.results == null) {
            throw NullPointerException("Data not found")
        }
    }

    fun ifError(x: MutableState<moviesLoadingState>, e: Exception) {
        x.value = x.value.copy(
            errorGettingMovies = "Error: ${e.message}"
        )
    }
    fun fetchAMovie(movieId:Int){
        viewModelScope.launch {
            try {
                val dataForMovie = getAMovieService.dwnldAMovie(movieId)
                _reqMovieLoadingState.value = _reqMovieLoadingState.value.copy(
                    movie = dataForMovie,
                    errorGettingMovie = null,
                    isloading = false
                )
                if(dataForMovie == null){
                    throw NullPointerException("Movie not found")
                }
            }catch (e: Exception){
                _reqMovieLoadingState.value = _reqMovieLoadingState.value.copy(
                    errorGettingMovie = "Error: ${e.message}"
                )
            }
        }
    }
    fun fetchMovieImages(movieId:Int){
        viewModelScope.launch {
            try {
                val imageData = getMovieImages.dwnldMovieImages(movieId)
                _reqImageLoadingState.value = _reqImageLoadingState.value.copy(
                    imgList = imageData.backdrops,
                    errorGettingImages = null,
                    isloading = false
                )
                if (imageData == null){
                    throw NullPointerException("Images not found")
                }
            }catch (e:Exception){
                _reqImageLoadingState.value = _reqImageLoadingState.value.copy(
                    errorGettingImages = "Error: ${e.message}"
                )
            }
        }
    }
    fun fetchMovieCredits(movieId:Int){
        viewModelScope.launch {
            try {
                val creditsData = getMovieCredits.dwnldMovieCredits(movieId)
                _reqCreditsLoadingState.value = _reqCreditsLoadingState.value.copy(
                    castList = creditsData.cast,
                    crewList = creditsData.crew,
                    errorGettingCredits = null,
                    isloading = false
                )
                if (creditsData == null){
                    throw NullPointerException("Images not found")
                }
            }catch (e:Exception){
                _reqCreditsLoadingState.value = _reqCreditsLoadingState.value.copy(
                    errorGettingCredits = "Error: ${e.message}"
                )
            }
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val dataInResponse_pop = popMoviesService.dwnldPopMovies()
                val dataInResponse_up = upMoviesService.dwnldUpMovies()
                val dataInResponse_action = actionMoviesService.dwnldActionMovies()
                val dataInResponse_romance = romanceMoviesService.dwnldRomanceMovies()


                copyChanges(_moviesDataLoadingState_pop, dataInResponse_pop)
                copyChanges(_moviesDataLoadingState_up, dataInResponse_up)
                copyChanges(_moviesDataLoadingState_action, dataInResponse_action)
                copyChanges(_moviesDataLoadingState_romance, dataInResponse_romance)


            } catch (e: Exception) {
                ifError(_moviesDataLoadingState_pop, e)
                ifError(_moviesDataLoadingState_up, e)
                ifError(_moviesDataLoadingState_action, e)
                ifError(_moviesDataLoadingState_romance, e)

            }
        }
    }


    data class moviesLoadingState(
        val isloading: Boolean = true,
        val list: List<movies> = emptyList(),
        val errorGettingMovies: String? = null
    )
    data class movieLState(
        val isloading:Boolean = true,
        val movie: movies? = null,
        val errorGettingMovie: String? = null
    )
    data class imagesLState(
        val isloading:Boolean = true,
        val imgList: List<movieImage> = emptyList(),
        val errorGettingImages: String? = null
    )
    data class creditsLState(
        val isloading: Boolean = true,
        val castList: List<movieCast> = emptyList(),
        val crewList: List<movieCrew> = emptyList(),
        val errorGettingCredits: String? = null
    )
}