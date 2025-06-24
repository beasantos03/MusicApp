package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesMusicaScreen(
    navController: NavController,
    viewModel: MusicaViewModel

) {
    val musica = viewModel.musicaSelecionada

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Música") }
            )
        }
    ) { padding ->
        if (musica != null) {
            Column(
                modifier = Modifier

                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Título: ${musica.titulo}", style = MaterialTheme.typography.titleLarge)
                Text("Artista: ${musica.artista}")
                Text("Género: ${musica.genero}")
                Text("Ano: ${musica.ano}")
                Text("Avaliação: ${musica.avaliacao} / 5")

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        navController.navigate("menu")
                    }) {
                        Text("Menu")
                    }
                    Button(onClick = {
                        navController.navigate("editar")
                    }) {
                        Text("Editar")
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhuma música selecionada.")
            }
        }
    }
}



