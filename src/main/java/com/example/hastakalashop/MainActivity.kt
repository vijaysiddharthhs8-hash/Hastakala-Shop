package com.example.hastakalashop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hastakalashop.ui.components.BottomNavigationBar
import com.example.hastakalashop.ui.navigation.Screen
import com.example.hastakalashop.ui.screens.*
import com.example.hastakalashop.ui.theme.HastaKalaShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HastaKalaShopTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Show BottomBar only on main app screens, not on the Splash screen
    val showBottomBar = currentRoute != null && currentRoute != Screen.Splash.route

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) { SplashScreen(navController) }
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.QuickBill.route) { QuickBillScreen(navController) }
            composable(Screen.Analytics.route) { AnalyticsScreen(navController) }
            composable(Screen.StockAlert.route) { StockAlertScreen(navController) }
            composable(Screen.IncomeLog.route) { IncomeLogScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    HastaKalaShopTheme {
        MainApp()
    }
}
