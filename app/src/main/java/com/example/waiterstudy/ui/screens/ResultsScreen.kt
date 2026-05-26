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
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.viewmodel.OrderViewModel
import com.example.waiterstudy.userData.UserData

@Composable
fun ResultsScreen(
    navController: NavController,
    viewModel : OrderViewModel,
    userData: UserData
) {

    val subject = userData.subject

    val totalTime =
        subject.orders.sumOf {
            (it.endTimeStamp - it.startTimeStamp)
        }

    val totalMistakes =
        subject.orders.sumOf {
            it.mistakes
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

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
                text = "Run ${subject.subjectId}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkButton, RoundedCornerShape(16.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Layout: ${subject.layout}", color = WhiteText)
            Text("Date: ${subject.dateText}", color = WhiteText)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Orders: ${subject.orders.size}", color = WhiteText)
            Text("Time: ${totalTime} ms", color = WhiteText)
            Text("Mistakes: $totalMistakes", color = WhiteText)
        }

        Button(
            onClick = {

                val ctx = navController.context

                userData.DownloadCsv(ctx)

                viewModel.cart.clear()

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