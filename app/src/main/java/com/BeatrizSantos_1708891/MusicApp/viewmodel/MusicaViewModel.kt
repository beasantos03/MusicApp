package com.BeatrizSantos_1708891.MusicApp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.BeatrizSantos_1708891.MusicApp.data.Musica
import androidx.compose.runtime.*


class MusicaViewModel : ViewModel() {

    private var proximoId = 1

    val listaMusicas = mutableStateListOf<Musica>()
    var musicaSelecionada by mutableStateOf<Musica?>(null)
        private set

    fun gerarNovoId(): Int {
        return proximoId++
    }

    fun adicionarMusica(
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
    }

    fun removerMusica(musica: Musica) {
        listaMusicas.remove(musica)
    }

    fun selecionarMusica(musica: Musica) {
        musicaSelecionada = musica
    }

}

