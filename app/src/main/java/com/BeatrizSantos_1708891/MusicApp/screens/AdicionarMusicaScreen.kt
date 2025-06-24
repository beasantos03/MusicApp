package com.BeatrizSantos_1708891.MusicApp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarMusicaScreen(
    navController: NavController,
    viewModel: MusicaViewModel
) {
    val context = LocalContext.current

    var titulo by remember { mutableStateOf("") }
    var artista by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var avaliacao by remember { mutableStateOf(0) }
    var ano by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Adicionar Música") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
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
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Avaliação:")
                Spacer(modifier = Modifier.width(8.dp))
                for (i in 1..5) {
                    IconButton(onClick = { avaliacao = i }) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Estrela $i",
                            tint = if (i <= avaliacao) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    if (titulo.isNotBlank() && artista.isNotBlank() && genero.isNotBlank() && ano.toIntOrNull() != null) {
                        viewModel.adicionarMusica(
                            context = context,
                            titulo = titulo,
                            artista = artista,
                            genero = genero,
                            avaliacao = avaliacao,
                            ano = ano.toInt()
                        )
                        Toast.makeText(context, "Música adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.navigate("lista")
                    } else {
                        Toast.makeText(context, "Preenche todos os campos corretamente", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Adicionar Música")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ✅ Botão "Voltar ao Menu Principal"
            Button(
                onClick = { navController.navigate("menu") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Voltar ao Menu Principal")
            }
        }
    }
}


