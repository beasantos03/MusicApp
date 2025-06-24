package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    val context = LocalContext.current // <--- necessário para guardar alterações

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
                            viewModel.removerMusica(context, musica) // <--- agora guarda a nova lista
                        },
                        onToggleFavorita = {
                            viewModel.alternarFavorita(musica)
                            viewModel.guardarMusicas(context) // também guarda se mudar favorito
                        }
                    )
                    Divider(color = Color.LightGray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("menu") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Voltar ao Menu Principal")
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

