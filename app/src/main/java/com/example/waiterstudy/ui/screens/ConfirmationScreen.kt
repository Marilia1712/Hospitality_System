package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.navigation.AppScreen
import androidx.compose.foundation.background
import com.example.waiterstudy.ui.theme.BackgroundGray
import com.example.waiterstudy.utils.OrderMatcher
import com.example.waiterstudy.viewmodel.OrderViewModel

@Composable
fun ConfirmationScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {

    val cart = viewModel.cart

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(16.dp)
    ) {

        Text(
            text = "Confirm order - Table ${viewModel.selectedTable}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ORDER LIST
        cart.forEach { (item, qty) ->
            Text(text = "${qty}x ${item.name}")
        }

        Spacer(modifier = Modifier.weight(1f))

        // ACTIONS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Back")
            }

            Button(onClick = {

                val isCorrect = OrderMatcher.isOrderCorrect(
                    tableNumber = viewModel.selectedTable,
                    currentOrder = cart
                )

                if (isCorrect) {
                    navController.navigate(AppScreen.Success.route)
                } else {
                    navController.navigate(AppScreen.Error.route)
                }

            }) {
                Text("SEND")
            }
        }
    }
}