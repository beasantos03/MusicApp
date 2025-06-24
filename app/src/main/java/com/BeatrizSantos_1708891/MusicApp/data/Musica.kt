package com.BeatrizSantos_1708891.MusicApp.data

data class Musica(
    val id: Int,
    val titulo: String,
    val artista: String,
    val genero: String,
    val ano: Int,
    val avaliacao: Int,
    val favorita: Boolean = false // <- NOVO campo
)
