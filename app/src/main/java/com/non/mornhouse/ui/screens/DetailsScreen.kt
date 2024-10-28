package com.non.mornhouse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.non.mornhouse.viewmodel.MainViewModel


@Composable
fun DetailsScreen(navController: NavController, mainViewModel: MainViewModel) {
    val movieList by mainViewModel.storedFacts.observeAsState(emptyList())
    val currentIndex by mainViewModel.currentIndex.observeAsState(0)
    val currentItem = movieList.getOrNull(currentIndex)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary).padding(top = 60.dp)
        ) {
            IconButton(
                onClick = {
                     navController.popBackStack()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentItem?.number?.toString() ?: "N/A",
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(10.dp),
                    fontSize = 80.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 90.sp
                )
                Text(
                    text = currentItem?.text ?: "No fact available.",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
            }
        }
    }

