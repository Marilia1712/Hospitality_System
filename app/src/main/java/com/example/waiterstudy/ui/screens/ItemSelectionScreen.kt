package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.data.MenuItems
import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.ui.theme.BackgroundGray
import com.example.waiterstudy.viewmodel.OrderViewModel

@Composable
fun ItemSelectionScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {

    val items = MenuItems.items

    var selectedItem by remember { mutableStateOf(items.first()) }
    var quantity by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(16.dp)
    ) {

        Text(
            text = "Place an order - Table ${viewModel.selectedTable}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ITEM GRID
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(items) { item ->

                Card(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clickable {
                            selectedItem = item
                            quantity = 1
                        }
                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(item.name)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // QUANTITY SELECTOR
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(onClick = {
                if (quantity > 1) quantity--
            }) {
                Text("-")
            }

            Text(
                text = "$quantity",
                style = MaterialTheme.typography.headlineMedium
            )

            Button(onClick = {
                quantity++
            }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

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
                viewModel.addItem(selectedItem, quantity)
            }) {
                Text("Add")
            }

            Button(onClick = {
                navController.navigate(AppScreen.Confirmation.route)
            }) {
                Text("Cart")
            }
        }
    }
}