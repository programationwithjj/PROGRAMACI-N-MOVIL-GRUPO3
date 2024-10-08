package com.example.rastreadordegastospersonales.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rastreadordegastospersonales.R
import com.example.rastreadordegastospersonales.model.Expense
import com.example.rastreadordegastospersonales.ui.theme.BlackColor
import com.example.rastreadordegastospersonales.ui.theme.BorderColor
import com.example.rastreadordegastospersonales.ui.theme.PrimaryColor
import com.example.rastreadordegastospersonales.ui.theme.SecondaryColor
import com.example.rastreadordegastospersonales.viewModel.ExpenseViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ExpenseViewModel) {
    val expenses by viewModel.expenses.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(text = "Crear gasto", icon = Icons.Default.Add, onClick = {
            navController.navigate("createExpense")
        })
        Spacer(modifier = Modifier.height(18.dp))
        BudgetInfo(3580000)
        Expenses(expenses)
    }
}

@Composable
fun PrimaryButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(50.dp)
            .shadow(2.dp, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BlackColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.padding(start = 2.dp))
            Text(text, color = SecondaryColor)
        }
    }
}

@Composable
fun BudgetInfo(budget: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Mi presupuesto",
            color = BlackColor,
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
        )
        Text(
            text = "$${NumberFormat.getInstance(Locale("ES")).format(budget)}",
            color = BlackColor,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Expenses(expenses: List<Expense>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Gastos",
            color = BlackColor,
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.W400,
            modifier = Modifier.padding(bottom = 10.dp, start = 20.dp)
        )
        expenses.forEach { expense ->
            ExpenseRow(expense)
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
fun ExpenseRow(expense: Expense) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(PrimaryColor)
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 3f
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = BorderColor,
                    start = Offset(x = 0f, y = y),
                    end = Offset(x = size.width, y = y),
                    strokeWidth = strokeWidth
                )
            }

    ) {
        Column(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 5.dp)
        ) {
            if (expense.imageRes != null) {
                Image(
                    painter = painterResource(id = expense.imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_expense_default),
                    contentDescription = null,
                    tint = SecondaryColor,
                    modifier = Modifier.size(80.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 20.dp)
        ) {
            Text(
                expense.name,
                color = BlackColor,
                fontWeight = FontWeight.W500
            )
            Text(expense.category, color = BlackColor)
            Text(expense.date, color = BlackColor)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End,
        ) {
            Button(
                onClick = { /* Abrir menu contextual para vista, edición y eliminación */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .padding(0.dp)
                    .height(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null,
                    tint = BlackColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}