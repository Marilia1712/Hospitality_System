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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.ui.theme.BackgroundGray
import com.example.waiterstudy.ui.theme.DarkButton
import com.example.waiterstudy.viewmodel.OrderViewModel
import com.example.waiterstudy.ui.theme.WhiteText
import com.example.waiterstudy.ui.theme.DarkText
import com.example.waiterstudy.ui.theme.BannerBlue
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TableSelectionScreen(
    navController: NavController,
    viewModel: OrderViewModel
) {

    val tables = (1..9).toList()
    var selectedTable by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(72.dp))

        Text(
            text = "Select a table",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(tables) { table ->

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .background(
                                if (selectedTable == table)
                                    BannerBlue
                                else
                                    DarkButton
                            )
                            .clickable {
                                selectedTable = table
                            },
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = table.toString(),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            color = WhiteText
                        )
                    }
                }
            }
        }

        // BOTTOM BANNER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .background(BannerBlue)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = if (selectedTable != null)
                    "Table $selectedTable"
                else
                    "Table -",
                style = MaterialTheme.typography.headlineSmall,
                color = WhiteText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    selectedTable?.let {
                        viewModel.selectedTable = it
                        navController.navigate(AppScreen.ItemSelection.route)
                    }
                },
                enabled = selectedTable != null,
                modifier = Modifier.weight(1f)
            ) {

                Text("Enter")
            }

        }
    }
}