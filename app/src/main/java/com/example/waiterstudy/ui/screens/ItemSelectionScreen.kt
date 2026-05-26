package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.waiterstudy.data.Item
import com.example.waiterstudy.data.MenuItems
import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.viewmodel.OrderViewModel
import com.example.waiterstudy.userData.UserData
import com.example.waiterstudy.ui.components.Banner2

@Composable
fun ItemSelectionScreen(
    navController: NavController,
    viewModel: OrderViewModel,
    userData: UserData
) {

    val items = MenuItems.items

    var selectedItem by remember { mutableStateOf<Item?>(null) }
    var quantity by remember { mutableStateOf(1) }

    // NEW SOURCE OF TRUTH
    val layout = userData.subject.layout

    val banner2 = @Composable {
        Banner2(
            quantity = quantity,
            cartIsEmpty = viewModel.cart.isEmpty(),
            selectedItemName = selectedItem?.name,

            onBack = {
                navController.popBackStack()
                viewModel.cart.clear()
            },

            onAdd = {
                selectedItem?.let { item ->

                    val currentQty = viewModel.cart[item] ?: 0
                    val newQty = currentQty + quantity

                    viewModel.addItem(item, newQty)
                }

                selectedItem = null
                quantity = 1
            },

            onCart = {
                navController.navigate(AppScreen.Confirmation.route)
            },

            onIncrease = { quantity++ },
            onDecrease = { if (quantity > 1) quantity-- }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(72.dp))

        Text(
            text = "Place an order",
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

        // TOP BANNER
        if (layout == "TOP_BANNER") {banner2()}

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(items) { item ->

                    Card(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable {
                                selectedItem = item
                                quantity = 1
                            },
                        colors = CardDefaults.cardColors(
                            containerColor =
                                if (selectedItem == item)
                                    BlueButton
                                else
                                    DarkButton
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Image(
                                painter = painterResource(id = item.imageRes),
                                contentDescription = item.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = item.name,
                                color = WhiteText,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        // BOTTOM BANNER
        if (layout == "BOTTOM_BANNER") {banner2()}
    }
}