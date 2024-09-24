package com.example.rastreadordegastospersonales.model

data class Expense(
    val name: String,
    val category: String,
    val date: String,
    val amount: Double,
    val imageRes: Int? = null,
)