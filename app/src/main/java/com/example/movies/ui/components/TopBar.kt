package com.example.movies.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movies.R

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun TopBar(
    searchOnclick:()->Unit,
    isActive: Boolean,
    screenheight : Int,
    screenwidth : Int,
    navHostController: NavHostController
) {
    var topbarheight by remember{
        mutableStateOf(70.dp)
    }
    val topheight = LocalConfiguration.current.screenHeightDp.dp
    topbarheight = if (isActive) topheight else 70.dp
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(topbarheight)){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(color = Color.White)
        ) {

            AnimatedVisibility(
                visible = !isActive,
                enter = fadeIn(initialAlpha = 0f, animationSpec = tween(1500)),
                exit = fadeOut(targetAlpha = 0f, animationSpec = tween(1000))
            ) {
                Row(modifier = Modifier.fillMaxHeight()) {
                    Image(
                        painter = painterResource(id = R.drawable.noun_movies_1397662),
                        contentDescription = "movies_list",
                        modifier = Modifier
                            .size(52.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .padding(top = 5.dp)
                    )

                    Text(
                        text = "Movies",
                        fontSize = 25.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 10.dp),
                        fontWeight = FontWeight.Bold
                    )

                }
//           }
            }
        }
        val width_of_screen = LocalConfiguration.current.screenWidthDp

        Box(modifier = Modifier.align(alignment = Alignment.TopEnd)
            .padding(start = 15.dp)) {
            SearchBar(
                isActive = isActive,
                onClick = searchOnclick, expandedWidth = width_of_screen.dp,
                screenheight = screenheight,
                screenwidth = screenwidth,
                navHostController = navHostController
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        searchOnclick =  {

        },
        false,
        10,
        10,
        rememberNavController()
    )
}