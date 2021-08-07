package com.example.movies

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movies.models.movie
import com.example.movies.repository.MoviesRepository
import com.example.movies.ui.components.Bottomnav
import com.example.movies.ui.components.TopBar
import com.example.movies.ui.theme.MoviesTheme

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navHostController: NavHostController
){
    MoviesTheme {

        val homeBodyHeight = LocalConfiguration.current.screenHeightDp - 70.dp.value.toInt() - 60.dp.value.toInt()

        MoviesTheme {
            // A surface container using the 'background' color from the theme
            var isActive by remember {
                mutableStateOf(false)
            }
            if (isActive) {
                BackHandler() {
                    isActive = !isActive
                }
            }
            Surface(color = MaterialTheme.colors.background) {

                val homeBodyHeight =
                    LocalConfiguration.current.screenHeightDp - 70.dp.value.toInt() - 60.dp.value.toInt()

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            TopBar(searchOnclick = {
                                isActive = !isActive
                            }, isActive = isActive)
                            HomeBody(
                                isActive = isActive,
                                hieght = homeBodyHeight,
                                navHostController = navHostController
                            )
                        }
                        Bottomnav(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                        )
                    }
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun HomeBody(
    isActive:Boolean,
    hieght:Int,
    navHostController: NavHostController
) {
    val top_rated_movielist = remember {
        mutableStateOf(ArrayList<movie>())
    }
    val context = LocalContext.current
    val popular_movielist = remember {
        mutableStateOf(ArrayList<movie>())
    }

    val upcoming_movielist = remember {
        mutableStateOf(ArrayList<movie>())
    }

    SideEffect{
        MoviesRepository.GetAllTopRatedMovies(
            context = context,
            object : MoviesRepository.ResonseCallback {
                override fun onResponseCallback(movie: ArrayList<movie>) {
                    top_rated_movielist.value = movie
                }

            },
            TOP_RATED
        )

        MoviesRepository.GetAllTopRatedMovies(
            context = context,
            object : MoviesRepository.ResonseCallback {
                override fun onResponseCallback(movie: ArrayList<movie>) {
                    popular_movielist.value = movie
                }

            },
            POPULAR
        )

        MoviesRepository.GetAllTopRatedMovies(
            context = context,
            object : MoviesRepository.ResonseCallback {
                override fun onResponseCallback(movie: ArrayList<movie>) {
                    upcoming_movielist.value = movie
//                    Log.d(TAG, movie.toString())
                }

            },
            UPCOMING
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = hieght.dp)
            .verticalScroll(state = rememberScrollState())
    ) {

//            MoviesOfCategory(category = "Latest ", movielist = repository.latest_movies)
//             Log.d(TAG,repository.top_rated_movies.toString()+repository.top_rated_movies.isEmpty().toString())

        AnimatedVisibility(visible = !isActive,
            enter = fadeIn(initialAlpha = 0f,animationSpec = tween(1500)),
            exit = fadeOut(targetAlpha = 0f,animationSpec = tween(1000))
        ){
            MoviesOfCategory(category = "Top-Rated", movielist = top_rated_movielist.value,navHostController = navHostController)
        }
        AnimatedVisibility(visible = !isActive,
            enter = fadeIn(initialAlpha = 0f,animationSpec = tween(1500)),
            exit = fadeOut(targetAlpha = 0f,animationSpec = tween(1000))
        ) {
            MoviesOfCategory(category = "Popular", movielist = popular_movielist.value,navHostController = navHostController)
        }

        AnimatedVisibility(visible = !isActive,
            enter = fadeIn(initialAlpha = 0f,animationSpec = tween(1500)),
            exit = fadeOut(targetAlpha = 0f,animationSpec = tween(1000))
        ) {
            MoviesOfCategory(category = "Upcoming", movielist = upcoming_movielist.value,navHostController = navHostController)
        }
//            MoviesOfCategory(category = "Upcoming", movielist = repository.upcoming_movies)

    }
}


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview()
@Composable
fun HomeScreenPreview() {
    val context = LocalContext.current


    MoviesTheme {

        val homeBodyHeight = LocalConfiguration.current.screenHeightDp - 70.dp.value.toInt() - 60.dp.value.toInt()

        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {
                Column(modifier = Modifier.fillMaxWidth()
                ) {
                    TopBar(searchOnclick = { /*TODO*/ }, isActive = false)
                    HomeBody(isActive = false,
                        hieght = homeBodyHeight,
                    rememberNavController())
                }
                Bottomnav(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun MoviesOfCategory(
    category: String,
    movielist: ArrayList<movie>,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        Text(
            text = category,
            fontSize = MaterialTheme.typography.h5.fontSize,
            fontWeight = MaterialTheme.typography.h5.fontWeight,
            modifier = Modifier.padding(7.dp)
        )
        if (movielist.size != 0) {
            LazyRow() {
                items(movielist) { item ->

                    MovieCard(
                        height = 250.dp,
                        width = 150.dp,
                        title = item.title,
                        genre =item.genre,
                        image = item.poster_image,
                        itemid = item.id,
                        item = item,
                        navHostController = navHostController
                    )


                }
            }
        }
    }

}