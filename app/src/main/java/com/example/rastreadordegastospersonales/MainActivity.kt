package com.example.rastreadordegastospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rastreadordegastospersonales.expense.CreateExpenseScreen
import com.example.rastreadordegastospersonales.home.HomeScreen
import com.example.rastreadordegastospersonales.ui.theme.RastreadorDeGastosPersonalesTheme
import com.example.rastreadordegastospersonales.viewModel.ExpenseViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RastreadorDeGastosPersonalesTheme {
                val navController = rememberNavController()
                val expenseViewModel: ExpenseViewModel = viewModel()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController, viewModel = expenseViewModel)
                    }
                    composable("createExpense") {
                        CreateExpenseScreen(
                            onExpenseCreated = { navController.popBackStack() },
                            viewModel = expenseViewModel
                        )
                    }
                }
            }
        }
    }
}

