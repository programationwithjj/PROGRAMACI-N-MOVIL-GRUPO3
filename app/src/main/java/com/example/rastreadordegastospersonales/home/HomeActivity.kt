package com.example.rastreadordegastospersonales.home

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rastreadordegastospersonales.ui.theme.BlackColor
import com.example.rastreadordegastospersonales.ui.theme.PrimaryColor
import com.example.rastreadordegastospersonales.ui.theme.SecondaryColor
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(text = "Crear gasto", icon = Icons.Default.Add)
        Spacer(modifier = Modifier.height(18.dp))
        BudgetInfo(3580000)
        Expenses()
    }
}

@Composable
fun PrimaryButton(text: String, icon: ImageVector) {
    Button(
        onClick = { /* Acción al hacer clic */ },
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mi presupuesto",
            color = BlackColor,
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
        )
        Text(
            text = "$${NumberFormat.getInstance(Locale("ES")).format(budget)}",
            modifier = Modifier.fillMaxSize(),
            color = BlackColor,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Expenses() {
    Text(
        text = "Gastos",
        color = BlackColor,
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
    )
}