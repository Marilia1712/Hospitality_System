package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.navigation.AppScreen
import androidx.compose.foundation.background
import com.example.waiterstudy.ui.theme.BackgroundGray
import com.example.waiterstudy.ui.theme.SuccessGreen
import com.example.waiterstudy.viewmodel.OrderViewModel
import kotlinx.coroutines.delay

@Composable
fun FinishScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {

    LaunchedEffect(Unit) {

        delay(3000)

        viewModel.cart.clear()
        viewModel.selectedTable = 0

        navController.navigate(AppScreen.Start.route) {
            popUpTo(0)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "✔",
            style = MaterialTheme.typography.displayLarge,
            color = SuccessGreen
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Finished",
            style = MaterialTheme.typography.headlineSmall,
            color = SuccessGreen
        )
    }
}