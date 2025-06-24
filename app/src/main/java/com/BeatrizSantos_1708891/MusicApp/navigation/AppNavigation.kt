package com.BeatrizSantos_1708891.MusicApp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.BeatrizSantos_1708891.MusicApp.screens.*
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel
import com.BeatrizSantos_1708891.MusicApp.screens.MenuPrincipalScreen
import com.BeatrizSantos_1708891.MusicApp.screens.ListaFavoritasScreen

@Composable
fun AppNavigation(navController: NavHostController, viewModel: MusicaViewModel) {
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuPrincipalScreen(navController, viewModel)
        }
        composable("lista") {
            ListaDeMusicasScreen(navController = navController, viewModel = viewModel)
        }
        composable("adicionar") {
            AdicionarMusicaScreen(navController = navController, viewModel = viewModel)
        }
        composable("detalhes") {
            DetalhesMusicaScreen(navController = navController, viewModel = viewModel)
        }
        composable("favoritas") {
            ListaFavoritasScreen(navController = navController, viewModel = viewModel)
        }
        composable("editar") {
            EditarMusicaScreen(navController = navController, viewModel = viewModel)
        }
    }
}
