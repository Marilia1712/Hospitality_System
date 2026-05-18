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
            .padding(16.dp)
    ) {

        Text(
            text = "Select a table",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(tables) { table ->

                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(
                            if (selectedTable == table) DarkButton else MaterialTheme.colorScheme.surface
                        )
                        .clickable {
                            selectedTable = table
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = table.toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Bottom banner (temporary simple version)
        Button(
            onClick = {
                selectedTable?.let {
                    viewModel.selectedTable = it
                    navController.navigate(AppScreen.ItemSelection.route)
                }
            },
            enabled = selectedTable != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enter")
        }
    }
}