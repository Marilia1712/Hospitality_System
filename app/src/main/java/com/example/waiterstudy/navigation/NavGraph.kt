package com.example.waiterstudy.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.waiterstudy.viewmodel.OrderViewModel

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val viewModel: OrderViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.TableSelection.route
    ) {

        composable(AppScreen.TableSelection.route) {
            // placeholder for now
        }

        composable(AppScreen.ItemSelection.route) {
            // placeholder for now
        }

        composable(AppScreen.Confirmation.route) {
            // placeholder for now
        }

        composable(AppScreen.Error.route) {
            // placeholder for now
        }

        composable(AppScreen.Success.route) {
            // placeholder for now
        }
    }
}