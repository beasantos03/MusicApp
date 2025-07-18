package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@Composable
fun MenuPrincipalScreen(navController: NavHostController, viewModel: MusicaViewModel) {
    val context = LocalContext.current

    // Carrega as músicas guardadas ao iniciar o ecrã
    LaunchedEffect(Unit) {
        viewModel.carregarMusicas(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBBDEFB))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // centralizado verticalmente
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo à MusicApp!", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("lista") }) {
            Text("Ver Lista de Músicas")
        }

        Button(onClick = { navController.navigate("adicionar") }) {
            Text("Adicionar Nova Música")
        }

        Button(onClick = { navController.navigate("favoritas") }) {
            Text("Ver Favoritas")
        }
    }
}

