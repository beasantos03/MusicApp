package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarMusicaScreen(
    navController: NavHostController,
    viewModel: MusicaViewModel
) {
    var titulo by remember { mutableStateOf("") }
    var artista by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") } // <- Novo campo
    var avaliacao by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Música") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = artista,
                onValueChange = { artista = it },
                label = { Text("Artista") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Género") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = ano,
                onValueChange = { ano = it },
                label = { Text("Ano") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < avaliacao) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { avaliacao = index + 1 }
                    )
                }
            }

            Button(
                onClick = {
                    val anoInt = ano.toIntOrNull() ?: 0
                    if (titulo.isNotBlank() && artista.isNotBlank() && genero.isNotBlank() && anoInt > 0) {
                        viewModel.adicionarMusica(
                            titulo = titulo,
                            artista = artista,
                            genero = genero,
                            avaliacao = avaliacao,
                            ano = anoInt
                        )
                        navController.navigate("lista")
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Adicionar Música")
            }
        }
    }
}

