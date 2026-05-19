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
import com.example.waiterstudy.R
import com.example.waiterstudy.data.Item
import com.example.waiterstudy.data.MenuItems
import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.viewmodel.OrderViewModel
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun ItemSelectionScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {

    val items = MenuItems.items

    var selectedItem by remember { mutableStateOf<Item?>(null) }
    var quantity by remember { mutableStateOf(1) }

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
            color = WhiteText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Table ${viewModel.selectedTable}",
            style = MaterialTheme.typography.titleMedium,
            color = WhiteText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

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
                                    BannerBlue
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .background(DarkButton)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // BACK (fixed square)
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(56.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = WhiteText),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.back_button),
                    contentDescription = "Back",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // QUANTITY (takes remaining space, but not oversized)
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Button(onClick = {
                    if (quantity > 1) quantity--
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
                }) {
                    Text("+")
                }
            }

            // ADD (normal flexible button, not weight-driven)
            Button(
                onClick = {
                    selectedItem?.let {
                        viewModel.addItem(it, quantity)
                    }
                    selectedItem = null
                    quantity = 1
                },
                colors = ButtonDefaults.buttonColors(containerColor = BlueButton),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Add")
            }

            // CART (fixed square)
            Button(
                onClick = {
                    navController.navigate(AppScreen.Confirmation.route)
                },
                modifier = Modifier.size(56.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = WhiteText),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = if (viewModel.cart.isEmpty())
                            R.drawable.empty_cart
                        else
                            R.drawable.full_cart
                    ),
                    contentDescription = "Cart",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}