package com.BeatrizSantos_1708891.musicapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesMusicaScreen(
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Título: ${musica.titulo}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Artista: ${musica.artista}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Género: ${musica.genero}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Ano: ${musica.ano}", style = MaterialTheme.typography.bodyLarge)
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


