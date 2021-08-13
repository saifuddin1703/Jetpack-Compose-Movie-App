package com.example.movies.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.movies.*
import com.example.movies.models.movie
import com.example.movies.models.movieDetail


object MoviesRepository {
    val TAG = "DEBUGGING"

    interface ResonseCallback {
        fun onResponseCallback(movie: ArrayList<movie>)
    }

    interface SearchErrorCallback {
        fun onSearchError(errorMessage:String)
    }

    interface MovieDetailCallback{
        fun onMovieDetailResponse(movie:movieDetail)
    }

    interface MoviePostersGetCallback{
        fun onMoviePosterGet(posterUrl:ArrayList<String>)
    }

    fun GetAllTopRatedMovies(context: Context, callback: ResonseCallback,category:String) {
        val url = URL + category + API_KEY

        val movies = ArrayList<movie>()
//        val list by lazy {
        val Jsonrequest = JsonObjectRequest(Request.Method.GET, url, null, {
            val jsonArray = it.getJSONArray("results")
            Log.d(TAG, url)
            for (i in 0 until jsonArray.length()) {
                val movieObject = jsonArray.getJSONObject(i)
                val id = movieObject.getInt("id")
                val language = movieObject.getString("original_language")
                val title = movieObject.getString("title")
                val poster = movieObject.getString("poster_path")
                val rating = movieObject.getString("vote_average").toFloat()
                val votes = movieObject.getString("vote_count").toInt()
                val genre = movieObject.getJSONArray("genre_ids")

                val genrelist = ArrayList<Int>()

                for (i in 0 until genre.length())
                {
                    val ob = genre.getInt(i)
                    genrelist.add(ob)
                }
                val movie = movie(
                    title = title,
                    id = id,
                    poster_image = IMAGE_URL + poster,
                    ratings = rating,
                    numbers_of_votes = votes,
                    language = language,
                    genre = genrelist
                )
//                    Log.d(TAG,movie.toString())
                movies.add(movie)
            }
            callback.onResponseCallback(movie = movies)


        }, {
            Log.d(TAG, "${it.message}")
        })
        MySingleton.getInstance(context).addToRequestQueue(Jsonrequest)
    }

    fun GetMovieDetails(movieId:Int?,context: Context,callback: MovieDetailCallback){
        val url = URL + MOVIE + "$movieId" + API_KEY

        val Jsonrequest = JsonObjectRequest(Request.Method.GET, url, null, {
            val movieObject = it
            Log.d(TAG, url)

            val id = movieObject.getString("id").toInt()
            val language = movieObject.getString("original_language")
            val title = movieObject.getString("title")
            val description = movieObject.getString("overview")
            val poster = movieObject.getString("poster_path")
            val rating = movieObject.getString("vote_average").toFloat()
            val votes = movieObject.getString("vote_count").toInt()
            val genre = movieObject.getJSONArray("genres")
            val runtime = movieObject.getString("runtime").toInt()
            val release_date = movieObject.getString("release_date")
            var genrelist = ""

            for (i in 0 until genre.length())
            {
                val ob = genre.getJSONObject(i)
               genrelist += ob.getString("name") + ", "
            }
            val movie = movieDetail(
                title = title,
                id = id,
                description = description,
                poster_image = IMAGE_URL + poster,
                ratings = rating,
                numbers_of_votes = votes,
                language = language,
                genre = genrelist,
                runtime = runtime,
                release_date = release_date
            )
              callback.onMovieDetailResponse(movie = movie)
        }, {
            Log.d(TAG, "${it.message}")
        })
        MySingleton.getInstance(context).addToRequestQueue(Jsonrequest)
    }

    fun SearchMovie(context: Context,movieName:String,callback: ResonseCallback,errorCallback: SearchErrorCallback){
    val url = "$URL$SEARCH$API_KEY&query=$movieName"
    val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,{
        val jsonArray = it.getJSONArray("results")
        Log.d(TAG, url)
        val results = ArrayList<movie>()
        for (i in 0 until jsonArray.length()) {
            val movieObject = jsonArray.getJSONObject(i)
            val id = movieObject.getInt("id")
            val language = movieObject.getString("original_language")
            val title = movieObject.getString("title")
            val poster = movieObject.getString("poster_path")
            val rating = movieObject.getString("vote_average").toFloat()
            val votes = movieObject.getString("vote_count").toInt()
            val genre = movieObject.getJSONArray("genre_ids")

            val genrelist = ArrayList<Int>()

            for (j in 0 until genre.length())
            {
                val ob = genre.getInt(j)
                genrelist.add(ob)
            }
            val movie = movie(
                title = title,
                id = id,
                poster_image = IMAGE_URL + poster,
                ratings = rating,
                numbers_of_votes = votes,
                language = language,
                genre = genrelist
            )
//                    Log.d(TAG,movie.toString())
            results.add(movie)
            callback.onResponseCallback(results)
        }
    },{
        it.message?.let { it1 -> errorCallback.onSearchError(it1) }
    })
//    {
//        override fun getParams(): MutableMap<String, String> {
//             return mutableMapOf("query" to movieName)
//        }
//    }
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun GetImagesOfTheMovie(movieId: Int,context: Context,callback: MoviePostersGetCallback){
        val url = URL + MOVIE + "$movieId" + IMAGES_ROUTE + API_KEY
        Log.d(TAG,url)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,{
            val posters = it.getJSONArray("posters")
           val posterList = ArrayList<String>()
            for (i in 0 until posters.length()){
                val posterObject = posters.getJSONObject(i)
               val posterPath = posterObject.getString("file_path")
                posterList.add(IMAGE_URL+posterPath)
                Log.d(TAG, IMAGE_URL+posterPath)
            }
            callback.onMoviePosterGet(posterUrl = posterList)

        },{
            Log.d(TAG,"${it.message}")
        })

        MySingleton.getInstance(context = context).addToRequestQueue(jsonObjectRequest)
    }
}