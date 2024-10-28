package com.non.mornhouse.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.non.mornhouse.ui.theme.MornhouseTheme
import com.non.mornhouse.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MornhouseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = "mainScreen",
                    ) {
                        composable("mainScreen") { MainScreen(navController, mainViewModel) }
                        composable("detailsScreen") { DetailsScreen(navController, mainViewModel) }
                    }
                }
            }
        }
    }
}

