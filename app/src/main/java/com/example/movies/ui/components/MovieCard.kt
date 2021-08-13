package com.example.movies

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
//import coil.compose.rememberImagePainter
//import coil.util.CoilUtils
import com.example.movies.models.movie
import com.example.movies.ui.theme.Shapes
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MovieCard(
    width: Dp,
    height: Dp,
    title: String,
    genre: ArrayList<Int>,
    image: String,
    itemid:Int,
    navHostController: NavHostController,
    item:movie
){
    var genreString = ""
    if (genre.size==1)
        genreString = getGenreById(genre[0])
    else
    for (i in genre){
        genreString+="${getGenreById(i)},"
    }
    val scope = rememberCoroutineScope()

    var xState by remember{
        mutableStateOf(width/2)
    }

    var yState by remember{
        mutableStateOf(height/2)
    }

    val xOffset by animateDpAsState(targetValue = xState,
    animationSpec = tween(1100))

    val yOffset by animateDpAsState(targetValue = yState,
        animationSpec = tween(1100))

    var widthstate by remember {
        mutableStateOf(0.dp)
    }
    var heightstate by remember {
        mutableStateOf(0.dp)
    }
    val widtha by animateDpAsState(targetValue = widthstate,
    animationSpec = tween(1100))
    val heighta by animateDpAsState(targetValue = heightstate,
        animationSpec = tween(1100))
    Box(
        modifier = Modifier
            .width(width)
            .height(height = height)
    ){
        Card(
            modifier = Modifier
                .width(widtha)
                .height(height = heighta)
                .padding(start = 10.dp)
                .offset(xOffset, yOffset),
            onClick = {
                Log.d("test",item.id.toString())
                navHostController.navigate("moviedetailscreen/${item.id}")
            }
        ) {


            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(data = image),
                    modifier = Modifier
                        .weight(4f)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(20.dp)),
                    contentDescription = "movie Poster",
                    contentScale = ContentScale.Crop
                    )

                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .padding(start = 8.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = genreString,
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.5f)
                )
            }


        }
    }
//    }
    LaunchedEffect(key1 = widthstate,) {
        scope.launch {
            delay(4)
           widthstate = width
            heightstate = height
            xState = 0.dp
            yState = 0.dp
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun MovieCardPreview(){
    MovieCard(
        width = 200.dp,
        height = 300.dp,
        title = "Movie title",
        genre = arrayListOf<Int>(),
        image = "",
       12,
        rememberNavController(),
        movie()
    )
}