package com.example.movies.models

data class movie(val title:String = "MovieTitle",
                 val description:String = "MovieDescription",
                 val id : Int = 0,
                 val genre:ArrayList<Int> = ArrayList(),
                 val language:String = "",
                 val poster_image:String = "",
                 val release_date:String = "",
                 val ratings:Float = 0f,
                 val numbers_of_votes:Int = 0
                 )
