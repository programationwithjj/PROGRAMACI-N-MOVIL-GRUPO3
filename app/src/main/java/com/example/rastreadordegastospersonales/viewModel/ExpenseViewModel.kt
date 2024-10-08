package com.example.rastreadordegastospersonales.viewModel

import androidx.lifecycle.ViewModel
import com.example.rastreadordegastospersonales.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExpenseViewModel : ViewModel() {
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    fun addExpense(expense: Expense) {
        _expenses.value = _expenses.value + expense
    }
}
