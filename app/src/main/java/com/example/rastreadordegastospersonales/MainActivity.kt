package com.example.rastreadordegastospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rastreadordegastospersonales.home.HomeScreen
import com.example.rastreadordegastospersonales.ui.theme.RastreadorDeGastosPersonalesTheme
import com.example.rastreadordegastospersonales.video.TutorialScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RastreadorDeGastosPersonalesTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawer(navController) {
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Rastreador de Gastos") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                if (drawerState.isOpen) {
                                    drawerState.close()
                                } else {
                                    drawerState.open()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                        }
                    },
                    actions = {
                    }
                )
            },
            content = { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    Modifier.padding(innerPadding)
                ) {
                    composable("home") { HomeScreen() }
                    composable("tutorial") { TutorialScreen() }
                }
            }
        )
    }
}

@Composable
fun NavigationDrawer(navController: NavHostController, onItemClick: () -> Unit) {
    val items = listOf("Home", "Tutoriales")

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0f))
        )

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .background(Color.White)
        ) {
            Column {
                items.forEach { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                when (item) {
                                    "Home" -> navController.navigate("home")
                                    "Tutoriales" -> navController.navigate("tutorial")
                                }
                                onItemClick()
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RastreadorDeGastosPersonalesTheme {
        MainScreen()
    }
}