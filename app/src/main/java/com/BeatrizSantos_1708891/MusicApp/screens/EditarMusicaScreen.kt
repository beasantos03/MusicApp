package com.BeatrizSantos_1708891.MusicApp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BeatrizSantos_1708891.MusicApp.viewmodel.MusicaViewModel

@Composable
fun EditarMusicaScreen(navController: NavController, viewModel: MusicaViewModel) {
    val context = LocalContext.current
    val musica = viewModel.musicaSelecionada

    // ⚠️ Se a música for nula, mostra erro e sai do Composable
    if (musica == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Erro: Nenhuma música selecionada para edição.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Voltar")
            }
        }
        return
    }

    // Inicializa campos com os dados da música selecionada
    var titulo by remember { mutableStateOf(musica.titulo) }
    var artista by remember { mutableStateOf(musica.artista) }
    var genero by remember { mutableStateOf(musica.genero) }
    var avaliacao by remember { mutableStateOf(musica.avaliacao.toString()) }
    var ano by remember { mutableStateOf(musica.ano.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editar Música", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(value = titulo, onValueChange = { titulo = it }, label = { Text("Título") })
        OutlinedTextField(value = artista, onValueChange = { artista = it }, label = { Text("Artista") })
        OutlinedTextField(value = genero, onValueChange = { genero = it }, label = { Text("Género") })
        OutlinedTextField(value = avaliacao, onValueChange = { avaliacao = it }, label = { Text("Avaliação (0-5)") })
        OutlinedTextField(value = ano, onValueChange = { ano = it }, label = { Text("Ano") })

        Button(onClick = {
            val musicaAtualizada = musica.copy(
                titulo = titulo,
                artista = artista,
                genero = genero,
                avaliacao = avaliacao.toIntOrNull() ?: 0,
                ano = ano.toIntOrNull() ?: 0
            )
            viewModel.atualizarMusica(context, musicaAtualizada)
            navController.popBackStack()
        }) {
            Text("Guardar Alterações")
        }

        OutlinedButton(onClick = {
            navController.popBackStack()
        }) {
            Text("Cancelar")
        }
    }
}
