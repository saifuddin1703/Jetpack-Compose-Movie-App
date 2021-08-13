package com.example.movies.ui.components

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movies.MovieCard
import com.example.movies.models.movie
import com.example.movies.repository.MoviesRepository

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun SearchResultsScreen(
    movieName:String,
    navHostController: NavHostController
){
    Log.d("Detail","errorMessage $movieName")

    var results by remember {
        mutableStateOf(ArrayList<movie>())
    }
    var error by remember {
        mutableStateOf("no error")
    }
//    SideEffect {
        MoviesRepository.SearchMovie(LocalContext.current, movieName = movieName,object : MoviesRepository.ResonseCallback{
            override fun onResponseCallback(movie: ArrayList<movie>) {
                results = movie
                error = "no error"
                Log.d("Detail",results.toString())

            }

        },object : MoviesRepository.SearchErrorCallback{
            override fun onSearchError(errorMessage: String) {
                    error = errorMessage
                Log.d("Detail",errorMessage+movieName)

            }

        })
//    }

    if (error == "no error"){
        LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
            items(results) { item: movie ->
                MovieCard(
                    width = 150.dp,
                    height = 300.dp,
                    title = item.title,
                    genre = item.genre,
                    image = item.poster_image,
                    itemid = item.id,
                    navHostController = navHostController,
                    item = item
                )
            }
        })
    }else{
        Text(text = "No results found for $movieName",
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center)
    }

}