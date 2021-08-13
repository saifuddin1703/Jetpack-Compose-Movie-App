package com.example.movies.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmeringEffect(){
//    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
//      drawPath(
//          Path().
//      )
//    })


    val brush = Brush.linearGradient(
        listOf(Color.Red, Color.Green, Color.Blue),
        start = Offset(10.dp.value, 50.dp.value),
        end = Offset(10.dp.value, 100.dp.value)
    )

    Box(modifier = Modifier.fillMaxSize()
        .background(brush = brush))
}


@Preview
@Composable
fun PreviewEffect(){
    ShimmeringEffect()
}