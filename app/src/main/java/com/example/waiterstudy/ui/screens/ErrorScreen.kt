package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.background
import com.example.waiterstudy.ui.theme.BackgroundGray
import com.example.waiterstudy.viewmodel.OrderViewModel

@Composable
fun ErrorScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(16.dp)
    ) {

        Text(
            text = "Incorrect order. Try again.",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        viewModel.cart.forEach { (item, qty) ->
            Text(text = "${qty}x ${item.name}")
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Back")
            }

            Button(
                onClick = { },
                enabled = false
            ) {
                Text("SEND")
            }
        }
    }
}