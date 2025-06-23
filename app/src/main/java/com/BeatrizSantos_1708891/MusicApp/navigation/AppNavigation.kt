package com.BeatrizSantos_1708891.MusicApp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel
import com.BeatrizSantos_1708891.MusicApp.screens.AdicionarMusicaScreen
import com.BeatrizSantos_1708891.MusicApp.screens.ListaDeMusicasScreen
import com.BeatrizSantos_1708891.MusicApp.screens.MenuPrincipalScreen
import com.BeatrizSantos_1708891.musicapp.screens.DetalhesMusicaScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel = MusicaViewModel()

    NavHost(navController = navController, startDestination = "menu") {
        composable(route = "menu") {
            MenuPrincipalScreen(navController)
        }
        composable(route = "lista") {
            ListaDeMusicasScreen(navController, viewModel)
        }
        composable(route = "adicionar") {
            AdicionarMusicaScreen(navController, viewModel)
        }
        composable(route = "detalhes") {
            DetalhesMusicaScreen(viewModel)
        }
    }
}

