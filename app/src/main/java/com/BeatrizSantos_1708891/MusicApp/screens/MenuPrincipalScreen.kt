package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MenuPrincipalScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo à MusicApp!", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = { navController.navigate("lista") }) {
            Text("Ver Lista de Músicas")
        }

        Button(onClick = { navController.navigate("adicionar") }) {
            Text("Adicionar Nova Música")
        }
    }
}
