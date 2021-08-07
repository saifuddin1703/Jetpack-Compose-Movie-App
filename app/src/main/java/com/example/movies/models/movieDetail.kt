package com.example.movies.models

data class movieDetail(val title:String = "MovieTitle",
                       val description:String = "MovieDescription",
                       val id : Int = 0,
                       val genre:String = "",
                       val language:String = "",
                       val poster_image:String = "",
                       val release_date:String = "",
                       val ratings:Float = 0f,
                       val numbers_of_votes:Int = 0,
                       val runtime:Int = 0
)
