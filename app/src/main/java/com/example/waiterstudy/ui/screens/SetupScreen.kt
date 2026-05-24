package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.viewmodel.ExperimentViewModel

@Composable
fun SetupScreen(
    navController: NavController,
    experimentViewModel: ExperimentViewModel
) {

    val runId = experimentViewModel.runId

    // IMPORTANT: sync with experiment state
    var selectedLayout by remember {
        mutableStateOf(experimentViewModel.layout)
    }

    val layouts = listOf("TOP_BANNER", "BOTTOM_BANNER")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Set up",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Run $runId",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = DarkText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Layout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(12.dp))

            layouts.forEach { layout ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(
                            if (selectedLayout == layout) BlueButton else DarkButton,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            selectedLayout = layout
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = layout,
                        color = WhiteText,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Button(
            onClick = {

                experimentViewModel.layout = selectedLayout
                experimentViewModel.runId = runId + 1

                navController.navigate(AppScreen.Start.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Confirm")
        }
    }
}