package com.example.rastreadordegastospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.rastreadordegastospersonales.home.HomeScreen
import com.example.rastreadordegastospersonales.ui.theme.RastreadorDeGastosPersonalesTheme
import com.example.rastreadordegastospersonales.video.TutorialScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RastreadorDeGastosPersonalesTheme {
                /**  TutorialScreen() **/
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RastreadorDeGastosPersonalesTheme {
        HomeScreen()
    }
}