package com.example.waiterstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.waiterstudy.navigation.NavGraph
import com.example.waiterstudy.ui.theme.WaiterStudyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WaiterStudyTheme {

                NavGraph()
            }
        }
    }
}