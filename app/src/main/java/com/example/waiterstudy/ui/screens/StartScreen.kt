package com.example.waiterstudy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.waiterstudy.R
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

        // TOP TITLE
        Text(
            text = "Ready?",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // CENTER PLAY AREA
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Yellow),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {

                            // RUN STARTS HERE
                            experimentViewModel.runStartTime =
                                System.currentTimeMillis()          // RUN STARTS HERE

                            experimentViewModel.totalClicks = 0
                            experimentViewModel.mistakes = 0
                            experimentViewModel.completedOrders = 0
                            experimentViewModel.orderResults.clear()
                            experimentViewModel.eventLogs.clear()

                            navController.navigate(AppScreen.TableSelection.route)
                        }
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.playbutton),
                        contentDescription = "Start",
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Start",
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
        }

        // BOTTOM INFO
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Run ${experimentViewModel.runId}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )

            Text(
                text = "Layout ${experimentViewModel.layout}",
                style = MaterialTheme.typography.titleSmall,
                color = DarkText
            )
        }
    }
}