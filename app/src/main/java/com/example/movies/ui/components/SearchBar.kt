package com.example.movies.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.movies.R

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SearchBar(
    isActive:Boolean,
    onClick:()->Unit,
    expandedWidth: Dp,
    screenheight : Int,
    screenwidth : Int,
    navHostController: NavHostController
){
    var rowwidthstate by remember {
        mutableStateOf(40.dp)
    }
    val rowwidth by animateDpAsState(targetValue = rowwidthstate,
        animationSpec = tween(1000)
    )

    var textfieldwidthstate by remember {
        mutableStateOf(0.dp)
    }
    val textfieldwidth by animateDpAsState(targetValue = textfieldwidthstate,
        animationSpec = tween(1000)
    )
    if (isActive)
    {
        rowwidthstate = expandedWidth
        textfieldwidthstate = expandedWidth - 40.dp

    }else{
        rowwidthstate = 40.dp
        textfieldwidthstate = 0.dp
    }



    Column {
        var searchString by remember {
            mutableStateOf("")
        }
        Row(modifier = Modifier.width(rowwidth)) {
            Image(
                painter = painterResource(id = R.drawable.noun_search_4115191),
                contentDescription = "search",
                modifier = Modifier
                    .width(40.dp)
                    .height(height = 40.dp)
                    .padding(end = 10.dp)
                    .clickable(true, onClick = onClick)
            )

            BasicTextField(
                value = searchString,
                onValueChange = { text ->
                    searchString = text
                },
                textStyle = TextStyle(fontSize = 15.sp), modifier = Modifier
                    .width(textfieldwidth)
                    .height(40.dp)
            )
        }
        var visible by remember {
            mutableStateOf(false)
        }
        visible = rowwidth == expandedWidth

        AnimatedVisibility(visible = visible) {
            Box(modifier = Modifier
                .height((screenheight - 40.dp.value.toInt()).dp)
                .width(screenwidth.dp)
                ) {
                SearchResultsScreen(movieName = searchString,navHostController = navHostController)
            }
        }
    }
}