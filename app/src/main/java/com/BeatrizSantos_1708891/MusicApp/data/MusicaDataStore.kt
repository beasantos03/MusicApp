package com.BeatrizSantos_1708891.MusicApp.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "musicas")

class MusicaDataStore(private val context: Context) {

    private val gson = Gson()
    private val musicasKey = stringPreferencesKey("musicas")

    fun obterMusicas(): Flow<List<Musica>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[musicasKey]
            if (json != null) {
                val tipo = object : TypeToken<List<Musica>>() {}.type
                gson.fromJson(json, tipo)
            } else {
                emptyList()
            }
        }
    }

    suspend fun guardarMusicas(musicas: List<Musica>) {
        val json = gson.toJson(musicas)
        context.dataStore.edit { preferences ->
            preferences[musicasKey] = json
        }
    }
}

