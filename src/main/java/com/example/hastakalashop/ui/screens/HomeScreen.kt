package com.example.hastakalashop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hastakalashop.ui.navigation.Screen
import com.example.hastakalashop.ui.theme.HastaKalaShopTheme

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column(modifier = Modifier.padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)) {
                Text(
                    text = "Namaste, Artisan!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Welcome to Hasta-Kala Shop",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.QuickBill.route) },
                containerColor = MaterialTheme.colorScheme.secondary,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Sale", tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            // Summary Cards with Gradients
            SummarySection()
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            QuickActionsGrid(navController)
        }
    }
}

@Composable
fun SummarySection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard(
                title = "Total Sales",
                value = "₹12,450",
                icon = Icons.Default.TrendingUp,
                gradient = Brush.verticalGradient(listOf(Color(0xFF81C784), Color(0xFF43A047))),
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = "Weekly Income",
                value = "₹3,200",
                icon = Icons.Default.AccountBalanceWallet,
                gradient = Brush.verticalGradient(listOf(Color(0xFF64B5F6), Color(0xFF1E88E5))),
                modifier = Modifier.weight(1f)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard(
                title = "Best Seller",
                value = "Fiber Bag",
                icon = Icons.Default.Star,
                gradient = Brush.verticalGradient(listOf(Color(0xFFFFB74D), Color(0xFFF57C00))),
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = "Low Stock",
                value = "2 Items",
                icon = Icons.Default.Warning,
                gradient = Brush.verticalGradient(listOf(Color(0xFFE57373), Color(0xFFD32F2F))),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatCard(title: String, value: String, icon: ImageVector, gradient: Brush, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(gradient).padding(16.dp)) {
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                Icon(icon, contentDescription = null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(24.dp))
                Column {
                    Text(text = title, fontSize = 12.sp, color = Color.White.copy(alpha = 0.9f), fontWeight = FontWeight.Medium)
                    Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun QuickActionsGrid(navController: NavController) {
    val actions = listOf(
        QuickAction("Quick Bill", Icons.Default.Bolt, Screen.QuickBill.route, Color(0xFFFFF3E0)),
        QuickAction("Stock Alert", Icons.Default.NotificationImportant, Screen.StockAlert.route, Color(0xFFFFEBEE)),
        QuickAction("Log Sales", Icons.Default.History, Screen.IncomeLog.route, Color(0xFFE8F5E9)),
        QuickAction("Shop Settings", Icons.Default.Settings, Screen.Profile.route, Color(0xFFE3F2FD))
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(actions.size) { index ->
            ActionItem(actions[index]) {
                navController.navigate(actions[index].route)
            }
        }
    }
}

@Composable
fun ActionItem(action: QuickAction, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.height(110.dp),
        shape = RoundedCornerShape(24.dp),
        color = action.backgroundColor,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(action.icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = action.title, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

data class QuickAction(val title: String, val icon: ImageVector, val route: String, val backgroundColor: Color)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HastaKalaShopTheme {
        HomeScreen(rememberNavController())
    }
}
