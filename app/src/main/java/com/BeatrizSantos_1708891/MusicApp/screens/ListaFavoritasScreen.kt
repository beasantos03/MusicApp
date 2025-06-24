package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel
import com.BeatrizSantos_1708891.MusicApp.data.Musica

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaFavoritasScreen(
    navController: NavController,
    viewModel: MusicaViewModel
) {
    val favoritas = viewModel.listaMusicas.filter { it.favorita }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Músicas Favoritas") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (favoritas.isEmpty()) {
                Text("Nenhuma música marcada como favorita.")
            } else {
                favoritas.forEach { musica ->
                    MusicaFavoritaItem(
                        musica = musica,
                        onToggleFavorita = {
                            viewModel.alternarFavorita(musica)
                            viewModel.guardarMusicas(context)
                        }
                    )
                    Divider()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Voltar")
            }
        }
    }
}

@Composable
fun MusicaFavoritaItem(
    musica: Musica,
    onToggleFavorita: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = musica.titulo, style = MaterialTheme.typography.titleMedium)
            Text(text = "Artista: ${musica.artista}")
            Text(text = "Género: ${musica.genero}")
            Text(text = "Avaliação: ${musica.avaliacao}/5")
        }

        IconButton(onClick = onToggleFavorita) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Remover dos Favoritos",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
