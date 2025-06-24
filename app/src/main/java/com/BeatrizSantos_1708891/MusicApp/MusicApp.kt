package com.BeatrizSantos_1708891.MusicApp

import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.BeatrizSantos_1708891.MusicApp.navigation.AppNavigation
import com.BeatrizSantos_1708891.MusicApp.ui.theme.MusicAppTheme
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@Composable
fun MusicApp() {
    val navController = rememberNavController()
    val viewModel: MusicaViewModel = viewModel() // Correto: dentro de função composable

    MusicAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AppNavigation(navController = navController, viewModel = viewModel)
        }
    }
}
