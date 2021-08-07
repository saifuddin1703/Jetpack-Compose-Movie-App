package com.example.movies

const val Action = 28
const val Adventure = 12
const val Animation = 16
const val Comedy = 35
const val Crime = 80
const val Documentary = 99
const val Drama = 18
const val Family = 10751
const val Fantasy = 14
const val History = 36
const val Horror = 27
const val Music = 10402
const val Mystery = 9648
const val Romance = 10749
const val Science_Fiction = 878
const val TV_Movie = 10770
const val Thriller = 53
const val War = 10752
const val Western = 37

fun getGenreById(id:Int):String{
    when(id){
        28 ->{
           return "Action"
        }
        12 ->{
            return "Adventure"
        }
        16 ->{
            return "Animation"
        }
        35 ->{
            return "Comedy"
        }
        80 ->{
            return "Crime"
        }
        99 ->{
            return "Documentary"
        }
        18 ->{
            return "Drama"
        }
        10751 ->{
            return "Family"
        }
        14 ->{
            return "Fantasy"
        }
        36 ->{
            return "History"
        }
        27 ->{
            return "Horror"
        }
        10402 ->{
            return "Music"
        }
        9648 ->{
            return "Mystery"
        }
        10749 ->{
            return "Romance"
        }
        878 ->{
            return "Sci-Fi"
        }
        10770 ->{
            return "TV_Movie"
        }
        53 ->{
            return "Thriller"
        }
        10752 ->{
            return "War"
        }
        37 ->{
            return "Western"
        }
    }
    return ""
}
