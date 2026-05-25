package com.example.waiterstudy.navigation

sealed class AppScreen(val route: String) {

    object Setup : AppScreen("setup")

    object Start : AppScreen("start")

    object TableSelection : AppScreen("table_selection")

    object ItemSelection : AppScreen("item_selection")

    object Confirmation : AppScreen("confirmation")

    object Error : AppScreen("error")

    object Success : AppScreen("success")

    object Results : AppScreen("results")
}