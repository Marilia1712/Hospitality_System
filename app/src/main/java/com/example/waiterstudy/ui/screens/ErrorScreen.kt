package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.ui.components.Banner4
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.viewmodel.OrderViewModel

@Composable
fun ErrorScreen(
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

        // ORDER LIST
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Incorrect order. Try again.",
                color = WhiteText,
                modifier = Modifier
                    .background(ErrorRed, shape= RoundedCornerShape(12.dp))
                    .padding(12.dp),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            cart.forEach { (item, qty) ->
                Text(
                    text = "${qty}x ${item.name}",
                    color = DarkText,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        //BOTTOM BANNER
        Banner4(
            onBack = {
                navController.popBackStack()
            },
            onSend = { }
        )
    }
}