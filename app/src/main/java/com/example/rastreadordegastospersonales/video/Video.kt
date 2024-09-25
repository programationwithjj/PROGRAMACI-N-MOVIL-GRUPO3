package com.example.rastreadordegastospersonales.video

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView
import com.example.rastreadordegastospersonales.R

@Composable
fun TutorialScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Habilitar desplazamiento vertical
            .padding(vertical = 8.dp)
    ) {
        // Primer Título
        Text(
            text = "Tutoriales",
            color = Color(0xFF6B6091),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 32.dp)
        )

        // Primer Card con reproductor de video local
        VideoCard(
            context = context,
            videoResId = R.raw.tutorial // Reemplaza con el nombre de tu video en res/raw
        )

        // Segundo Título
        Text(
            text = "5 Métodos de ahorro",
            color = Color(0xFF6B6091),
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )

        // Descripción
        Text(
            text = "Aprende 5 métodos de ahorro que verdaderamente funcionan y cómo aplicarlos en tu vida diaria para convertirlos en poderosos hábitos de gestión financiera exitosa....",
            color = Color(0xFF49454F),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 0.dp, bottom = 32.dp)
        )

        // Segundo Card con reproductor de video local
        VideoCard(
            context = context,
            videoResId = R.raw.metodos_ahorro 
        )

        // Tercer Título
        Text(
            text = "Educación financiera para toda la vida",
            color = Color(0xFF6B6091),
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )

        // Descripción
        Text(
            text = "Aprende ¿Por qué la Educación Financiera es indispensable para los ciudadanos? y como evitar errores que se generan por el desconocimiento...",
            color = Color(0xFF49454F),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 0.dp, bottom = 32.dp)
        )
    }
}

@Composable
fun VideoCard(context: Context, videoResId: Int) {
    // Crear el ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/$videoResId")
            setMediaItem(mediaItem)
            prepare()
        }
    }

    // Destruir el ExoPlayer cuando el Composable salga de la composición
    DisposableEffect(
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 48.dp)
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}
