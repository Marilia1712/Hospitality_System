package com.example.waiterstudy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.waiterstudy.ui.theme.*

@Composable
fun Banner3(
    onBack: () -> Unit,
    onSend: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .background(DarkButton)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // BACK
        Button(
            onClick = onBack,
            modifier = Modifier.size(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WhiteText),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("<") // placeholder (you can replace with icon if you want)
        }

        // SEND (fills remaining space)
        Button(
            onClick = onSend,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = BlueButton)
        ) {
            Text("SEND")
        }
    }
}