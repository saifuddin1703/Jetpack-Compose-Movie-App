package com.example.movies

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.movies.HomeBody
import com.example.movies.HomeScreen
import com.example.movies.MovieCard
import com.example.movies.R
import com.example.movies.models.movie
import com.example.movies.ui.components.MovieDetailScreen
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    val TAG = "DEBUGGING"

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
               val navController = rememberNavController()
                HostScreen(navController = navController)
                
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HostScreen(
    navController: NavHostController
){
    NavHost(navController = navController,startDestination = "homescreen"){
        composable("homescreen"){
            HomeScreen(navHostController = navController)
        }
        composable("moviedetailscreen/{movieid}",
         arguments = listOf(navArgument("movieid"){type = NavType.StringType
         defaultValue = " "}))
        {
            val id = it.arguments?.getString("movieid")?.toInt()
            Log.d("test",id.toString())
            MovieDetailScreen(movieInt = id,navController)
        }
    }
}





    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Preview()
    @Composable
    fun DefaultPreview() {
        val context = LocalContext.current
       MoviesTheme {
            val navController = rememberNavController()
            HostScreen(navController = navController)

        }
    }





