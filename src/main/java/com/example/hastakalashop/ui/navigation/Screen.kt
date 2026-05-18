package com.example.hastakalashop.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object QuickBill : Screen("quick_bill")
    object Analytics : Screen("analytics")
    object StockAlert : Screen("stock_alert")
    object IncomeLog : Screen("income_log")
    object Profile : Screen("profile")
}
