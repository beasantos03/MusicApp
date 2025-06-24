package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BeatrizSantos_1708891.MusicApp.data.Musica
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeMusicasScreen(
    navController: NavController,
    viewModel: MusicaViewModel
) {
    val context = LocalContext.current
    val termoPesquisa = viewModel.termoPesquisa

    LaunchedEffect(Unit) {
        viewModel.carregarMusicas(context)
    }

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
            TextField(
                value = termoPesquisa,
                onValueChange = { viewModel.termoPesquisa = it },
                label = { Text("Pesquisar por título ou artista") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            val musicasFiltradas = viewModel.listaMusicas.filter {
                it.titulo.contains(termoPesquisa, ignoreCase = true) ||
                        it.artista.contains(termoPesquisa, ignoreCase = true)
            }

            if (musicasFiltradas.isEmpty()) {
                Text("Nenhuma música encontrada.")
            } else {
                musicasFiltradas.forEach { musica ->
                    MusicaItem(
                        musica = musica,
                        onClick = {
                            viewModel.selecionarMusica(musica)
                            navController.navigate("detalhes")
                        },
                        onDelete = {
                            viewModel.removerMusica(context, musica)
                        },
                        onToggleFavorita = {
                            viewModel.alternarFavorita(musica)
                            viewModel.guardarMusicas(context)
                        },
                        onEditar = {
                            viewModel.selecionarMusica(musica)
                            navController.navigate("editar") // nova rota para editar
                        }
                    )
                    Divider(color = Color.LightGray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navController.navigate("menu") }) {
                    Text("Menu Principal")
                }
                Button(onClick = { navController.navigate("favoritas") }) {
                    Text("Ver Favoritas")
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
    onToggleFavorita: () -> Unit,
    onEditar: () -> Unit
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

        IconButton(onClick = onEditar) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Editar",
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Button(onClick = onDelete) {
            Text("Eliminar")
        }
    }
}

