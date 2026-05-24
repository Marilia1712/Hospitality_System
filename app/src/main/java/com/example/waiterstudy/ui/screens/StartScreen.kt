package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.viewmodel.ExperimentViewModel

@Composable
fun StartScreen(
    navController: NavController,
    experimentViewModel: ExperimentViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Run ${experimentViewModel.runId}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Layout: ${experimentViewModel.layout}",
                style = MaterialTheme.typography.titleMedium,
                color = DarkText
            )
        }

        Button(
            onClick = {

                experimentViewModel.runStartTime =
                    System.currentTimeMillis()

                experimentViewModel.totalClicks = 0
                experimentViewModel.mistakes = 0
                experimentViewModel.orderResults.clear()
                experimentViewModel.eventLogs.clear()

                navController.navigate(AppScreen.TableSelection.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "START RUN",
                fontWeight = FontWeight.Bold
            )
        }
    }
}