package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.navigation.AppScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.waiterstudy.ui.components.Banner3
import com.example.waiterstudy.ui.theme.BackgroundGray
import com.example.waiterstudy.ui.theme.DarkButton
import com.example.waiterstudy.ui.theme.DarkText
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

        // HEADER
        Text(
            text = "Confirm order",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Table ${viewModel.selectedTable}",
            style = MaterialTheme.typography.titleMedium,
            color = DarkText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ORDER RECAP AREA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),   // THIS is the key
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkButton, shape = RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                cart.forEach { (item, qty) ->

                    var quantity by remember { mutableStateOf(qty) }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = item.name,
                            color = WhiteText,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(120.dp)
                        )

                        Row(
                            modifier = Modifier
                                .background(WhiteText, shape = RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

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
                                color = DarkText,
                                fontWeight = FontWeight.Bold,
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
            }
        }

        // BOTTOM BANNER
        Banner3(
            onBack = { navController.popBackStack() },
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