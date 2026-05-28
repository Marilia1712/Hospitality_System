package com.example.waiterstudy.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.waiterstudy.ui.screens.*
import com.example.waiterstudy.viewmodel.OrderViewModel
import com.example.waiterstudy.userData.UserData

@Composable
fun NavGraph(
    navController: NavHostController,
    userData: UserData
) {
    val orderViewModel: OrderViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Setup.route
    ) {
        composable(AppScreen.Setup.route) {
            SetupScreen(
                navController = navController,
                userData = userData
            )
        }

        composable(AppScreen.Start.route) {
            StartScreen(
                navController = navController,
                userData = userData
            )
        }

        composable(AppScreen.TableSelection.route) {
            TableSelectionScreen(
                navController = navController,
                viewModel = orderViewModel,
                userData = userData
            )
        }

        composable(AppScreen.ItemSelection.route) {
            ItemSelectionScreen(
                navController = navController,
                viewModel = orderViewModel,
                userData = userData
            )
        }

        composable(AppScreen.Confirmation.route) {
            ConfirmationScreen(
                navController = navController,
                viewModel = orderViewModel,
                userData = userData
            )
        }

        composable(AppScreen.Error.route) {
            ErrorScreen(
                navController = navController,
                viewModel = orderViewModel,
                userData = userData
            )
        }

        composable(AppScreen.Success.route) {
            SuccessScreen(
                navController = navController,
                viewModel = orderViewModel,
                userData = userData
            )
        }

        composable(AppScreen.Results.route) {
            ResultsScreen(
                navController = navController,
                viewModel = orderViewModel,
                userData = userData
            )
        }
    }
}