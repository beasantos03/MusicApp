package com.BeatrizSantos_1708891.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import com.BeatrizSantos_1708891.MusicApp.navigation.AppNavigation
import com.BeatrizSantos_1708891.MusicApp.ui.theme.MusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicApp()
        }
    }
}

@Composable
fun MusicApp() {
    MusicAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            AppNavigation(navController = navController)
        }
    }
}
