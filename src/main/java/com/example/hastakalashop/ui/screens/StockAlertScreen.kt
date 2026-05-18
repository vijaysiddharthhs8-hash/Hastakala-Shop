package com.example.hastakalashop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hastakalashop.ui.theme.HastaKalaShopTheme

@Composable
fun StockAlertScreen(navController: NavController) {
    val lowStockItems = listOf(
        StockItem("Handcraft Basket", 2, "Critical"),
        StockItem("Keychain - Indigo", 5, "Low"),
        StockItem("Decorative Mirror", 3, "Critical")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Inventory Alerts",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The following items are running low. Please restock soon.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(lowStockItems) { item ->
                    StockAlertCard(item)
                }
            }
        }
    }
}

@Composable
fun StockAlertCard(item: StockItem) {
    val isCritical = item.status == "Critical"
    val cardColor = if (isCritical) Color(0xFFFFEBEE) else Color(0xFFFFFDE7)
    val indicatorColor = if (isCritical) Color(0xFFD32F2F) else Color(0xFFFBC02D)

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Warning,
                contentDescription = null,
                tint = indicatorColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "Remaining: ${item.quantity} units", style = MaterialTheme.typography.bodySmall)
            }
            Button(
                onClick = { /* Restock logic */ },
                colors = ButtonDefaults.buttonColors(containerColor = indicatorColor),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text("Restock", fontSize = 12.sp)
            }
        }
    }
}

data class StockItem(val name: String, val quantity: Int, val status: String)

@Preview(showBackground = true)
@Composable
fun StockAlertScreenPreview() {
    HastaKalaShopTheme {
        StockAlertScreen(rememberNavController())
    }
}
