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
import com.example.waiterstudy.storage.CsvExporter
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.viewmodel.ExperimentViewModel

@Composable
fun ResultsScreen(
    navController: NavController,
    experimentViewModel: ExperimentViewModel
) {

    val run = experimentViewModel.buildRunData()

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
                text = "Results",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Run ${run.runId}", color = DarkText)
            Text("Layout: ${run.layout}", color = DarkText)
            Text("Duration: ${run.totalDurationMs} ms", color = DarkText)
            Text("Clicks: ${run.totalClicks}", color = DarkText)
            Text("Orders: ${run.completedOrders}", color = DarkText)
            Text("Mistakes: ${run.mistakes}", color = DarkText)
        }

        Column {

            Button(
                onClick = {

                    val ctx = navController.context

                    CsvExporter.exportRun(ctx, run)
                    CsvExporter.exportOrders(ctx, experimentViewModel.orderResults)
                    CsvExporter.exportEvents(ctx, experimentViewModel.eventLogs)

                    experimentViewModel.resetRun()
                    experimentViewModel.runId += 1

                    navController.navigate(AppScreen.Setup.route) {
                        popUpTo(0)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("EXPORT & NEW RUN")
            }
        }
    }
}