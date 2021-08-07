package com.example.movies.ui.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.movies.models.movieDetail
import com.example.movies.repository.MoviesRepository
import com.example.movies.R
import com.example.movies.models.movie

@Composable
fun MovieDetailScreen(
    movieInt: Int?
){
    val context = LocalContext.current
    var movieDetail by remember {
        mutableStateOf<movieDetail>(movieDetail())
    }
    SideEffect {
        MoviesRepository.GetMovieDetails(movieId = movieInt,
        context =context,
        callback = object : MoviesRepository.MovieDetailCallback{
            override fun onMovieDetailResponse(movie: movieDetail) {
                 movieDetail = movie
                Log.d("Detail",movieDetail.toString())
            }

        })
    }

    val runtime = "${movieDetail.runtime/60}h ${movieDetail.runtime%60}min"
     Box(modifier = Modifier
         .fillMaxSize()
         ){

         val height = LocalConfiguration.current.screenHeightDp
         Image(painter = rememberImagePainter(movieDetail.poster_image),
             contentDescription = "Movie Image",
         modifier = Modifier
             .height((height * 0.5).dp)
             .fillMaxWidth()
         ,contentScale = ContentScale.FillWidth
         )

         Image(painter = painterResource(id = R.drawable.save),
             contentDescription = "save this movie",
         modifier = Modifier
             .align(alignment = Alignment.TopEnd)
             .size(60.dp)
             .padding(all = 15.dp),
         )

         Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
             contentDescription = "back button",
             modifier = Modifier
                 .align(alignment = Alignment.TopStart)
                 .size(60.dp)
                 .padding(all = 15.dp)
                 .clickable {

                 }
         )


         Box(
             modifier = Modifier
                 .align(alignment = Alignment.BottomCenter)
                 .height((height * 0.6).dp)
                 .fillMaxWidth()
                 .background(
                     color = Color.White,
                     shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                 )
         ){
             Column(modifier = Modifier
                 .fillMaxWidth()
                 .fillMaxHeight()
                 .padding(start = 40.dp, end = 40.dp,top = 25.dp)
                 .verticalScroll(rememberScrollState())) {

                 Text(text = movieDetail.title,
                 fontWeight = MaterialTheme.typography.h6.fontWeight,
                 fontSize = MaterialTheme.typography.h4.fontSize,
                 modifier = Modifier.padding(top = 10.dp))

                 Text(text = movieDetail.genre,
                     fontWeight = MaterialTheme.typography.h5.fontWeight,
                     fontSize = 16.sp,
                     color = Color.Gray
                 )

                 Text(text = "Runtime : $runtime",
                     fontWeight = MaterialTheme.typography.h5.fontWeight,
                     fontSize = 16.sp,
                     color = Color.Gray
                 )

                 Text(
                     text = movieDetail.description,
                     modifier = Modifier
                         .fillMaxSize()
                         .padding(top = 8.dp),
                     fontWeight = MaterialTheme.typography.h5.fontWeight,
                     fontSize = 18.sp,
                     color = Color.DarkGray,
                     lineHeight = 25.sp
                 )

                 Spacer(modifier = Modifier.fillMaxWidth()
                     .height(80.dp))

             }
         }

         Button(onClick = { /*TODO*/ },
             modifier = Modifier
                 .align(alignment = Alignment.BottomStart)
                 .padding(start = 20.dp, bottom = 10.dp)
                 .width(250.dp)
                 .height(60.dp)
                 ,
             shape =  RoundedCornerShape(20.dp),
             colors = ButtonDefaults.buttonColors(
                 backgroundColor = Color.Black,
                 contentColor = Color.White
             )
         ) {
             Text(text = "Watch Now",
                 color = Color.White,
             fontSize = MaterialTheme.typography.h5.fontSize)
         }
     }


}

@Preview
@Composable
fun preview(){
    MovieDetailScreen(movieInt = 15)
}