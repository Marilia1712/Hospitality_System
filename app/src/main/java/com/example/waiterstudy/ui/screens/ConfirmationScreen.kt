package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.ui.components.Banner3
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.userData.UserData
import com.example.waiterstudy.utils.OrderMatcher
import com.example.waiterstudy.viewmodel.OrderViewModel
import com.example.waiterstudy.data.Item

@Composable
fun ConfirmationScreen(
    navController: NavController,
    viewModel: OrderViewModel,
    userData: UserData
) {

    val cart = viewModel.cart
    val layout = userData.subject.layout

    fun sendOrder() {

        val startTime = System.currentTimeMillis()

        val isCorrect = OrderMatcher.isOrderCorrect(
            tableNumber = viewModel.selectedTable,
            currentOrder = cart
        )

        val order = com.example.waiterstudy.userData.OrderData(
            orderId = userData.subject.orders.size + 1,
            startTimeStamp = startTime,
            endTimeStamp = System.currentTimeMillis(),
            mistakes = if (isCorrect) 0 else 1
        )

        userData.subject.orders.add(order)

        if (!isCorrect) {
            navController.navigate(AppScreen.Error.route)
            return
        }

        val finished = userData.subject.orders.size >= 4

        if (finished) {
            navController.navigate(AppScreen.Results.route) {
                popUpTo(0)
            }
        } else {
            navController.navigate(AppScreen.Success.route)
        }
    }

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

        if (layout == "TOP_BANNER") {
            Banner3(
                onBack = { navController.popBackStack() },
                onSend = { sendOrder() }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CART + EDITING UI
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkButton, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                cart.forEach { (item, qty) ->

                    var quantity by remember(item) { mutableStateOf(qty) }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = item.name,
                            color = WhiteText,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(120.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(WhiteText, shape = RoundedCornerShape(12.dp))
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                        ) {

                            Button(
                                onClick = {
                                    if (quantity > 0) {
                                        quantity--
                                        viewModel.updateItem(item, quantity)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = DarkButton,
                                    contentColor = WhiteText
                                ),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text("-")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = quantity.toString(),
                                color = DarkText,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    quantity++
                                    viewModel.updateItem(item, quantity)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = DarkButton,
                                    contentColor = WhiteText
                                ),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text("+")
                            }
                        }
                    }
                }
            }
        }

        if (layout == "BOTTOM_BANNER") {
            Banner3(
                onBack = { navController.popBackStack() },
                onSend = { sendOrder() }
            )
        }
    }
}