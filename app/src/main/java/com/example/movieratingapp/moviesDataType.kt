package com.example.movieratingapp


data class movies(
    val adult:Boolean,
    val backdrop_path:String,
    val id:Int,
    val original_language:String,
    val original_title: String,
    val overview:String,
    val popularity:Double,
    val poster_path:String,
    val release_date:String,
    val vote_average:Double,
    val status:String,
    val runtime:Int,
    val genres: List<movieGenre>
)

data class moviesList(val results: List<movies>)
data class movieGenre(
    val id: Int,
    val name: String
)

data class movieImage(
    val aspect_ratio: Double,
//    val height: Int,
//    val iso_639_1: null,
    val file_path: String,
//    val vote_average: Float,
//    val vote_count: Int,
//    val width: Int
)
data class movieImageList(val backdrops: List<movieImage>)

data class movieCast(
    val name: String,
    val profile_path: String,
    val character: String
)
data class movieCrew(
    val name: String,
    val job: String
)
data class movieCreditsList(
    val cast: List<movieCast>,
    val crew: List<movieCrew>
)