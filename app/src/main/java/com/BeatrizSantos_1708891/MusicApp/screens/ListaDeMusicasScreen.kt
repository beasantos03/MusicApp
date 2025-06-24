package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel
import com.BeatrizSantos_1708891.MusicApp.data.Musica
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeMusicasScreen(
    navController: NavController,
    viewModel: MusicaViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Músicas") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (viewModel.listaMusicas.isEmpty()) {
                Text("Nenhuma música adicionada.")
            } else {
                viewModel.listaMusicas.forEach { musica ->
                    MusicaItem(
                        musica = musica,
                        onClick = {
                            viewModel.selecionarMusica(musica)
                            navController.navigate("detalhes")
                        },
                        onDelete = {
                            viewModel.removerMusica(musica)
                        },
                        onToggleFavorita = {
                            viewModel.alternarFavorita(musica)
                        }
                    )
                    HorizontalDivider(color = Color.LightGray)
                }
            }
        }
    }
}

@Composable
fun MusicaItem(
    musica: Musica,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onToggleFavorita: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
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
                imageVector = if (musica.favorita) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Favorita",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Button(onClick = onDelete) {
            Text("Eliminar")
        }
    }
}

