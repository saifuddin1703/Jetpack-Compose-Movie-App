package com.example.movies.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.R

@ExperimentalAnimationApi
@Composable
fun SearchBar(
    isActive:Boolean,
    onClick:()->Unit,
    expandedWidth: Dp
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
        var searchString by remember {
            mutableStateOf("")
        }
        BasicTextField(value = searchString,
            onValueChange = {text->
              searchString = text
            },
            textStyle = TextStyle(fontSize = 15.sp)
            ,modifier = Modifier
            .width(textfieldwidth)
            .height(40.dp))
    }
}