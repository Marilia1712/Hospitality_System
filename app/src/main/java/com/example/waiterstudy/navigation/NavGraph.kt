package com.example.waiterstudy.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.waiterstudy.viewmodel.OrderViewModel
import com.example.waiterstudy.ui.screens.TableSelectionScreen
import com.example.waiterstudy.ui.screens.ItemSelectionScreen
import com.example.waiterstudy.ui.screens.ConfirmationScreen
import com.example.waiterstudy.ui.screens.ErrorScreen
import com.example.waiterstudy.ui.screens.SuccessScreen


@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val viewModel: OrderViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.TableSelection.route
    ) {

        composable(AppScreen.TableSelection.route) {
            TableSelectionScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(AppScreen.ItemSelection.route) {
            ItemSelectionScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(AppScreen.Confirmation.route) {
            ConfirmationScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(AppScreen.Error.route) {
            ErrorScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(AppScreen.Success.route) {
            SuccessScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}