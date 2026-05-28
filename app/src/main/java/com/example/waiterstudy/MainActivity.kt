package com.example.waiterstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.waiterstudy.navigation.NavGraph
import com.example.waiterstudy.ui.theme.WaiterStudyTheme
import com.example.waiterstudy.userData.UserData

class MainActivity : ComponentActivity() {

    private val userDataViewModel: UserData by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WaiterStudyTheme {
                val navController = rememberNavController()

                // Keep this completely clean. No pointerInput tracking here!
                NavGraph(
                    navController = navController,
                    userData = userDataViewModel
                )
            }
        }
    }
}