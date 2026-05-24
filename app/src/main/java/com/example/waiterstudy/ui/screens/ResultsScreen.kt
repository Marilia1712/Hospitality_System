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

        // TOP SECTION
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Results",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Run ${run.runId}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // CENTER STATS CARD
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkButton, shape = RoundedCornerShape(16.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Layout: ${run.layout}",
                color = WhiteText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Time: ${run.totalDurationMs} ms",
                color = WhiteText
            )

            Text(
                text = "Clicks: ${run.totalClicks}",
                color = WhiteText
            )

            Text(
                text = "Orders: ${run.completedOrders}",
                color = WhiteText
            )

            Text(
                text = "Mistakes: ${run.mistakes}",
                color = WhiteText
            )
        }

        // BOTTOM BUTTON
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
            Text("EXPORT & NEW RUN", fontWeight = FontWeight.Bold)
        }
    }
}