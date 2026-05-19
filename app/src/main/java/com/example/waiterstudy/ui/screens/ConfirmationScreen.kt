package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.navigation.AppScreen
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.waiterstudy.ui.components.Banner3
import com.example.waiterstudy.ui.theme.BackgroundGray
import com.example.waiterstudy.ui.theme.WhiteText
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
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(72.dp))

        Text(
            text = "Confirm order",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = WhiteText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Text(
            text = "Table ${viewModel.selectedTable}",
            style = MaterialTheme.typography.titleMedium,
            color = WhiteText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // CENTERED ORDER LIST WITH QUANTITY SELECTOR
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            cart.forEach { (item, qty) ->

                var quantity by remember { mutableStateOf(qty) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {

                    Text(
                        text = item.name,
                        color = WhiteText,
                        modifier = Modifier.width(120.dp)
                    )

                    Button(onClick = {
                        if (quantity > 0) {
                            quantity--
                            viewModel.updateItem(item, quantity)
                        }
                    }) {
                        Text("-")
                    }

                    Text(
                        text = "$quantity",
                        color = WhiteText,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Button(onClick = {
                        quantity++
                        viewModel.updateItem(item, quantity)
                    }) {
                        Text("+")
                    }
                }
            }
        }

        Banner3(
            onBack = {
                navController.popBackStack()
            },
            onSend = {

                val isCorrect = OrderMatcher.isOrderCorrect(
                    tableNumber = viewModel.selectedTable,
                    currentOrder = cart
                )

                if (isCorrect) {
                    navController.navigate(AppScreen.Success.route)
                } else {
                    navController.navigate(AppScreen.Error.route)
                }
            }
        )
    }
}