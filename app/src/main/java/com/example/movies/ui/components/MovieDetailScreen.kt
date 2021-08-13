package com.example.movies.ui.components

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.movies.models.movieDetail
import com.example.movies.repository.MoviesRepository
import com.example.movies.R
import com.example.movies.TAG
import com.example.movies.models.movie
import com.example.movies.ui.Ratingbar
import com.example.movies.ui.theme.TransparentWhite
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun MovieDetailScreen(
    movieInt: Int?,
    navHostController: NavHostController
){

    val context = LocalContext.current
    var movieDetail by remember {
        mutableStateOf<movieDetail>(movieDetail())
    }
    var movieImages by remember {
        mutableStateOf(ArrayList<String>())
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

        MoviesRepository.GetImagesOfTheMovie(movieId = movieInt!!,
        context = context,
        callback = object : MoviesRepository.MoviePostersGetCallback{
            override fun onMoviePosterGet(posterUrl: ArrayList<String>) {
                 movieImages = posterUrl
            }
        })

    }
    val height1 = LocalConfiguration.current.screenHeightDp

    val state = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { (height1*0.4f) }
    val anchors = mapOf(0f to 0, sizePx to 1)

    val runtime = "${movieDetail.runtime/60}h ${movieDetail.runtime%60}min"
     BoxWithConstraints(modifier = Modifier
         .fillMaxSize()
         ) {

         val height = LocalConfiguration.current.screenHeightDp
         Image(
             painter = rememberImagePainter(movieDetail.poster_image),
             contentDescription = "Movie Image",
             modifier = Modifier
                 .height((height * 0.5).dp)
                 .fillMaxWidth(), contentScale = ContentScale.FillWidth
         )

         Image(
             painter = painterResource(id = R.drawable.save),
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
                     navHostController.popBackStack()
                 }
         )

           val density = LocalDensity.current.density

            var boxheight by remember {
                mutableStateOf(600.dp)
            }

         val topoffset = 60*density

         val maxOffsetvalue = (maxHeight).value
         var offsetY by remember {
             mutableStateOf(maxOffsetvalue - 0.1*density)
         }

         // detail sheet
         Box(
             modifier = Modifier
                 .offset { IntOffset(0, offsetY.roundToInt()) }
                 .height(maxHeight)
                 .draggable(
                     state = rememberDraggableState(onDelta = { delta ->

                         if (offsetY <= maxHeight.value && offsetY >= topoffset) {
                             offsetY += delta
                         } else {
                             if (offsetY >= maxHeight.value)
                                 offsetY -= 0.1*density

                             if (offsetY <= topoffset)
                                 offsetY += 0.1*density

                         }
                     }),
                     orientation = Orientation.Vertical
                 )
                 .fillMaxWidth()
                 .background(
                     color = Color.White,
                     shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                 )

         ) {
//             Box(modifier = Modifier.width(50.dp)
//                 .padding(top = 15.dp)
//                 .height(5.dp)
//                 .background(color = Color.LightGray,shape = RoundedCornerShape(5.dp))
//                 .align(alignment = Alignment.TopCenter))
             Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .fillMaxHeight()
                     .padding(start = 40.dp, end = 40.dp, top = 45.dp)
                     .verticalScroll(rememberScrollState())
             ) {

                 Text(
                     text = movieDetail.title,
                     fontWeight = MaterialTheme.typography.h6.fontWeight,
                     fontSize = MaterialTheme.typography.h4.fontSize,
                     modifier = Modifier.padding(top = 10.dp)
                 )

                 Text(
                     text = movieDetail.genre,
                     fontWeight = MaterialTheme.typography.h5.fontWeight,
                     fontSize = 16.sp,
                     color = Color.Gray,
                     modifier = Modifier.padding(top = 10.dp)
                 )

                 Text(
                     text = "Runtime : $runtime",
                     fontWeight = MaterialTheme.typography.h5.fontWeight,
                     fontSize = 16.sp,
                     color = Color.Gray,
                     modifier = Modifier.padding(top = 10.dp)
                 )

                 Text(
                     text = movieDetail.description,
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(top = 8.dp),
                     fontWeight = MaterialTheme.typography.h5.fontWeight,
                     fontSize = 18.sp,
                     color = Color.DarkGray,
                     lineHeight = 26.sp
                 )

                 Text(text = "Images",
                     fontSize = MaterialTheme.typography.h5.fontSize,
                     modifier = Modifier.padding(top = 10.dp)
                 )

                 Log.d(TAG,movieImages.toString())
                 LazyRow(content = {
                     items(movieImages){item ->
                        Image(
                            painter = rememberImagePainter(item),
                            contentDescription = "Movie Images",
                            modifier = Modifier
                                .padding(
                                    start = 10.dp
                                )
                                .size(150.dp),
                            contentScale = ContentScale.Crop
                        )
                     }
                 })
                 Spacer(
                     modifier = Modifier
                         .fillMaxWidth()
                         .height(80.dp)
                 )

             }
         }

         Box(modifier = Modifier
             .background(color = TransparentWhite)
             .align(alignment = Alignment.BottomCenter)
             .fillMaxWidth()) {
             Button(
                 onClick = { /*TODO*/ },
                 modifier = Modifier
                     .align(alignment = Alignment.BottomStart)
                     .padding(start = 20.dp, bottom = 10.dp)
                     .width(250.dp)
                     .height(60.dp),
                 shape = RoundedCornerShape(20.dp),
                 colors = ButtonDefaults.buttonColors(
                     backgroundColor = Color.Black,
                     contentColor = Color.White
                 )
             ) {
                 Text(
                     text = "Watch Now",
                     color = Color.White,
                     fontSize = MaterialTheme.typography.h5.fontSize
                 )
             }
             Ratingbar(
                 modifier = Modifier
                     .padding(end = 18.dp, bottom = 12.dp)
                     .size(70.dp)
                     .align(alignment = Alignment.BottomEnd), rating = movieDetail.ratings
             )
         }
     }


}

@ExperimentalMaterialApi
@Preview
@Composable
fun MovieDetaitlPreview(){
    MovieDetailScreen(movieInt = 15, rememberNavController())
}



@ExperimentalMaterialApi
@Composable
fun SwipeableSample() {
    val hieght = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = squareSize.value
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .height(hieght)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                orientation = Orientation.Vertical
            )
            .background(Color.LightGray)
    ) {
        val height = 48.dp.value + 24 * swipeableState.progress.fraction
       Column(
           modifier = Modifier.height(96.dp)
       ) {
          val density = LocalDensity.current.density
           var offsetY by remember {
               mutableStateOf(48*density - 0.5f)
           }
           var boxheight by remember {
               mutableStateOf(48.dp)
           }
           Box(modifier = Modifier
               .width(squareSize)
               .height(boxheight)
               .offset { IntOffset(0, offsetY.roundToInt()) }
               .draggable(
                   state = rememberDraggableState(onDelta = { delta ->
                       if (offsetY <= 48 * density && offsetY >= 0) {
                           offsetY += delta
                           boxheight -= delta.dp
                       } else {
                           if (offsetY > 48 * density)
                               offsetY = 48 * density

                           if (offsetY < 0f)
                               offsetY = 0f

                       }
                   }), orientation = Orientation.Vertical
               )
               .background(color = Color.DarkGray)
           )
        }
    }
}