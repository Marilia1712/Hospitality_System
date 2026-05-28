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
import com.example.waiterstudy.ui.components.TrackedScreen
import com.example.waiterstudy.ui.theme.*
import com.example.waiterstudy.userData.UserData

@Composable
fun StartScreen(
    navController: NavController,
    userData: UserData
) {
    TrackedScreen(screenName = "Start", userData = userData) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
                .padding(16.dp),

            verticalArrangement = Arrangement.SpaceBetween,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // HEADER
            Text(
                text = "Ready?",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            // PLAY AREA
            Column(
                modifier = Modifier.weight(1f),

                horizontalAlignment = Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Yellow),

                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier.clickable {

                            /*
                            Reset order tracking
                            before new run starts
                            */
                            userData.order.startTimeStamp = System.currentTimeMillis()
                            userData.order.orderNr = 1

                            navController.navigate(
                                AppScreen.TableSelection.route
                            )

                        },

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(
                                id = R.drawable.playbutton
                            ),

                            contentDescription = "Start",

                            modifier = Modifier.size(64.dp),

                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Start",

                    fontWeight = FontWeight.Bold,

                    color = DarkText
                )
            }

            // RUN INFO
            Column(
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text =
                        "Layout ${userData.subject.layout}",

                    style =
                        MaterialTheme.typography.titleSmall,

                    color = DarkText
                )
            }
        }
    }
}