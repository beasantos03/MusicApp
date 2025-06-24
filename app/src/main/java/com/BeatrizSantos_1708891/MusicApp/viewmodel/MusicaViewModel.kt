package com.BeatrizSantos_1708891.MusicApp.viewmodel

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.BeatrizSantos_1708891.MusicApp.data.Musica
import com.BeatrizSantos_1708891.MusicApp.data.MusicaDataStore  // <--- AQUI
import kotlinx.coroutines.launch

class MusicaViewModel : ViewModel() {
    private var proximoId = 1

    val listaMusicas = mutableStateListOf<Musica>()
    var musicaSelecionada by mutableStateOf<Musica?>(null)
        private set

    fun gerarNovoId(): Int {
        return proximoId++
    }

    fun adicionarMusica(
        context: Context,
        titulo: String,
        artista: String,
        genero: String,
        avaliacao: Int,
        ano: Int
    ) {
        val novaMusica = Musica(
            id = gerarNovoId(),
            titulo = titulo,
            artista = artista,
            genero = genero,
            avaliacao = avaliacao,
            ano = ano
        )
        listaMusicas.add(novaMusica)
        guardarMusicas(context)
    }

    fun removerMusica(context: Context, musica: Musica) {
        listaMusicas.remove(musica)
        guardarMusicas(context)
    }

    fun selecionarMusica(musica: Musica) {
        musicaSelecionada = musica
    }

    fun carregarMusicas(context: Context) {
        val dataStore =  MusicaDataStore(context)
        viewModelScope.launch {
            dataStore.obterMusicas().collect { musicas: List<Musica> ->
                listaMusicas.clear()
                listaMusicas.addAll(musicas)
                proximoId = (listaMusicas.maxOfOrNull { it.id } ?: 0) + 1
            }
        }
    }

    fun guardarMusicas(context: Context) {
        val dataStore = MusicaDataStore(context)
        viewModelScope.launch {
            dataStore.guardarMusicas(listaMusicas)
        }
    }

    fun alternarFavorita(musica: Musica) {
        val index = listaMusicas.indexOfFirst { it.id == musica.id }
        if (index != -1) {
            val atualizada = musica.copy(favorita = !musica.favorita)
            listaMusicas[index] = atualizada
        }
    }

    fun limparSelecao() {
        musicaSelecionada = null
    }
}

