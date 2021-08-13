package com.example.movies.ui

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Ratingbar(
    modifier:Modifier,
    rating:Float
){
    BoxWithConstraints(modifier = modifier,
    ){
    val width = maxWidth
        Canvas(modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(all = 5.dp)) {
                val radius = maxWidth/2
               val sweepangle = (360/10) * rating
            drawArc(
                startAngle = -90f,
                sweepAngle = sweepangle,
                color = Color.Black,
                useCenter = false,
                style = Stroke(width = 5.dp.toPx(),cap = StrokeCap.Round)
            )
        }

        Text(text = "$rating",
        modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Preview
@Composable
fun RatingPreview(){
    Ratingbar(modifier = Modifier.size(50.dp),
    rating = 8.7f)
}