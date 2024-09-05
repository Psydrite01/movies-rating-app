package com.example.movieratingapp

import android.adservices.adid.AdId
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

fun give2digitaverage(num:Double):Int{
    var temp:Int = 0
    while (temp<num*10){temp++}
    return temp
}
fun giveGenresAsString(list: List<movieGenre>):String{
    var theString:String = ""
    for (i in list){
        theString = theString+ i.name +", "
    }
    if (theString != null){
        theString = theString.dropLast(2)
    }
    return theString
}
fun giveCrewAsSting(crew:List<movieCrew>):String{
    var temp:String= ""
    for (i in crew){
        if (i.job == "Director"
            || i.job =="Producer"
            || i.job == "Executive Producer"
            || i.job =="Co-Director"
            || i.job == "Writer"){

            temp = temp + "${i.job} : ${i.name} \n"
        }
    }
    return temp
}



@Composable
fun showMovie(theId: Int, goToHomeUi:()->Unit){
    //for movie data
    val reqMovieViewModel: mainViewModel = viewModel()
    reqMovieViewModel.fetchAMovie(theId)
    val reqMovieViewState by reqMovieViewModel.reqMovieLoadingState
    var theMovie = reqMovieViewState.movie

    //for movie images
    val reqImageViewModel:mainViewModel = viewModel()
    reqImageViewModel.fetchMovieImages(theId)
    val reqImageViewState by reqImageViewModel.reqImageLoadingState
    var theImages = reqImageViewState.imgList

    //for movie credits
    val reqCreditsViewModel:mainViewModel = viewModel()
    reqCreditsViewModel.fetchMovieCredits(theId)
    val reqCreditsViewState by reqCreditsViewModel.reqCreditsLoadingState
    var theCast = reqCreditsViewState.castList
    var theCrew = reqCreditsViewState.crewList
    var crewAsString = giveCrewAsSting(theCrew)

    var posterPath:String="https://image.tmdb.org/t/p/w300_and_h450_bestv2" + (theMovie?.poster_path ?: "")
    var backDropPath:String="https://image.tmdb.org/t/p/w300_and_h450_bestv2" + (theMovie?.backdrop_path ?: "")
    var original_title = ""
    var original_language = ""
    var isAdult = false
    var overview = ""
    var popularity = 0.0
    var release_date = ""
    var vote_average :Double= 0.0
    var genres: List<movieGenre> = emptyList()
    var genresString: String = ""
    var status:String = ""
    var runtime:Int = 0
    if (theMovie != null) {
        original_title = theMovie.original_title
        original_language = theMovie.original_language
        isAdult = theMovie.adult
        overview = theMovie.overview
        popularity = theMovie.popularity
        release_date = theMovie.release_date
        vote_average = theMovie.vote_average
        vote_average = (give2digitaverage(vote_average).toDouble())/10
        genres = theMovie.genres
        genresString = giveGenresAsString(genres)
        status = theMovie.status
        runtime = theMovie.runtime
    }

    Box(modifier = Modifier
        .fillMaxSize()
        ){
        Image(
            painter = rememberAsyncImagePainter(backDropPath),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.66667f)
                .graphicsLayer {
                    translationY = -200f
                }
        )
        Box (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)){
                Row (
                    modifier=Modifier
                        .fillMaxWidth()
                ){
                    Button(onClick = {
                        goToHomeUi()
                    }, modifier = Modifier
                        .background(color = Color.Transparent),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White)) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Google",
                            tint = Color.White,
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .size(30.dp))
                    }
                }
            }

            Box(modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            translationY = -50f
                        }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter("https://www.pngkey.com/png/full/98-985656_black-gradient-png.png"),
                        contentDescription = "background",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(700.dp)
                            .border(width = 2.dp, color = Color(0xFF080A0F), shape = RectangleShape))

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(0xFF080A0F))
                            .padding(10.dp)
//                            .border(
//                                width = 5.dp,
//                                color = Color.Transparent,
//                                shape = RectangleShape
//                            )
                            .graphicsLayer {
                                translationY = -600f
                            }
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(178.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(text = "${original_title}", fontSize = 26.sp)
                                Text(text = "")
                                Text(text = "$status : $release_date")
                                Text(text = "Language: $original_language")
                                Text(text = "Rating: $vote_average /10")
                            }
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .aspectRatio(0.6666666666666f)
                            ){
                                Image(
                                    painter = rememberAsyncImagePainter(posterPath),
                                    contentDescription = "poster",
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                        .fillMaxWidth())
                            }

                        }
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp))
                        Text(text = "Runtime: "+runtime/60 +"h "+runtime%60 +"min")
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp))
                        Text(text = "Synopsis:")
                        Text(text = "$overview")
                        Text(text = "\nGenre: $genresString\n")
                        Spacer(modifier = Modifier.padding(15.dp))
                        LazyRow {
                            items(theImages){
                                showImage(it)
                            }
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = "The Cast:", modifier = Modifier
                            .padding(top=15.dp, bottom = 15.dp))
                        LazyRow {
                            items(theCast){
                                showCast(it)
                            }
                        }
                        Text(text = "The Crew:", modifier = Modifier
                            .padding(top = 25.dp, bottom = 15.dp, start = 5.dp))
                        Text(text = crewAsString, modifier = Modifier.padding(5.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun showImage(theImg:movieImage, ){
    Box(
        modifier = Modifier
            .padding(5.dp)
            .width(280.dp)
            .aspectRatio(1.778f)){
        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/original${theImg.file_path}"),
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds)
    }
}

@Composable
fun showCast(cast: movieCast){
    Box(
        modifier = Modifier
            .height(170.dp)
            .aspectRatio(0.75f)){
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(0.75f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/original${cast.profile_path}"),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(75.dp)
                    .padding(2.dp)
                    .clip(shape = CircleShape)
            )
            Text(text = cast.name, fontSize = 11.sp, textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 3.dp))
            Text(text = cast.character, fontSize = 11.sp, textAlign = TextAlign.Center)
        }
    }
}

