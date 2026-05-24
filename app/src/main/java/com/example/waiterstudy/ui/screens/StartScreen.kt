package com.example.waiterstudy.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.userData.UserData

@Composable
fun StartScreen(
    navController: NavController,
    userData: UserData
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            userData.newSubject()
            userData.addOrderData()
            navController.navigate(AppScreen.TableSelection.route)
        }){
            Text(text = "Start")
        }
    }
}
