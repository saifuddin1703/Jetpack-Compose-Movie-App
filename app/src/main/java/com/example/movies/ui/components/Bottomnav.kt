package com.example.movies.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.ui.theme.Shapes

@Composable
fun Bottomnav(
    modifier: Modifier
){
    BottomNavigation(modifier = modifier
        ,backgroundColor = Color.White,
    elevation = 0.dp) {
        var firsttabwidthstate by remember {
            mutableStateOf(0.dp)
        }
        var secondtabwidthstate by remember {
            mutableStateOf(0.dp)
        }
        var thirdtabwidthstate by remember {
            mutableStateOf(0.dp)
        }
        var selectedTab by remember {
            mutableStateOf(0)
        }

        val firstTabwidth by animateDpAsState(targetValue = firsttabwidthstate,
            animationSpec = tween(300)
        )

        val secondTabwidth by animateDpAsState(targetValue = secondtabwidthstate,
            animationSpec = tween(300)
        )

        val thirdTabwidth by animateDpAsState(targetValue = thirdtabwidthstate,
            animationSpec = tween(300)
        )




        BoxWithConstraints(modifier = Modifier
            .weight(1f)
            .fillMaxHeight()){
            val width = maxWidth - 40.dp -20.dp
            if (selectedTab == 0){
                firsttabwidthstate = width
                secondtabwidthstate = 0.dp
                thirdtabwidthstate = 0.dp
            }
            Row(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clip(shape = Shapes.medium)
                    .clickable {
                        firsttabwidthstate = width
                        secondtabwidthstate = 0.dp
                        thirdtabwidthstate = 0.dp
                        selectedTab = 0
                    }
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.noun_feed_2351448),
                    contentDescription = "saved movies",
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "Feeds",
                    modifier = Modifier
                        .height(40.dp)
                        .width(firstTabwidth)
                )

            }
        }

        BoxWithConstraints(modifier = Modifier
            .weight(1f)
            .fillMaxHeight()) {
            val width = maxWidth - 40.dp - 20.dp

            Row(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clip(shape = Shapes.medium)
                    .clickable {
                        firsttabwidthstate = 0.dp
                        secondtabwidthstate = width
                        thirdtabwidthstate = 0.dp
                        selectedTab = 1
                    }
                    .align(Alignment.Center)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.noun_save_3489181),
                    contentDescription = "saved movies",
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "Save",
                    modifier = Modifier
                        .height(40.dp)
                        .width(secondTabwidth)
                )

            }
        }

        BoxWithConstraints(modifier = Modifier
            .weight(1f)
            .fillMaxHeight()) {
            val width = maxWidth - 40.dp - 20.dp

            Row(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clip(shape = Shapes.medium)
                    .clickable {
                        firsttabwidthstate = 0.dp
                        secondtabwidthstate = 0.dp
                        thirdtabwidthstate = width
                        selectedTab = 2
                    }
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.noun_profile_3635583),
                    contentDescription = "saved movies",
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = "Profile",
                    modifier = Modifier
                        .height(40.dp)
                        .width(thirdTabwidth)
                )

            }
        }

    }
}

@Preview
@Composable
fun BottomnavPreview(){
    Bottomnav(modifier = Modifier.fillMaxWidth())
}