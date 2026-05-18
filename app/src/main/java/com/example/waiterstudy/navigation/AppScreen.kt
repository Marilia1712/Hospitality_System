package com.example.waiterstudy.navigation

sealed class AppScreen(val route: String) {

    data object TableSelection : AppScreen("table_selection")

    data object ItemSelection : AppScreen("item_selection")

    data object Confirmation : AppScreen("confirmation")

    data object Error : AppScreen("error")

    data object Success : AppScreen("success")
}