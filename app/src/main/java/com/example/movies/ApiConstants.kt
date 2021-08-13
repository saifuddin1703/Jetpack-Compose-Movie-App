package com.example.movies

const val URL = "https://api.themoviedb.org/3"
const val API_KEY = "?api_key=c9557a715017982aa0b9e038233404ea"
const val IMAGE_URL = "https://image.tmdb.org/t/p/original"
const val POPULAR = "/movie/popular"
const val TOP_RATED = "/movie/top_rated"
const val LATEST = "/movie/latest"
const val UPCOMING = "/movie/upcoming"
const val MOVIE = "/movie/"
const val SEARCH = "/search/movie"
const val NOW_PLAYING = "/movie/now_playing"

// we first need to mention the initial route for this images route i.e /movies/{movie_id}
const val IMAGES_ROUTE = "/images"

// https://api.themoviedb.org/3/movie/latest?api_key=c9557a715017982aa0b9e038233404ea