package com.example.waiterstudy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.waiterstudy.R
import com.example.waiterstudy.ui.theme.*

@Composable
fun Banner4(
    onBack: () -> Unit,
    onSend: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .background(DarkButton, shape= RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // BACK
        Button(
            onClick = onBack,
            modifier = Modifier.size(56.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WhiteText),
            shape = RoundedCornerShape(10.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.back_button),
                contentDescription = "Back",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // SEND (disabled + red)
        Button(
            onClick = { },
            enabled = false,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ErrorRed,
                disabledContainerColor = ErrorRed,
                disabledContentColor = WhiteText
            )
        ) {
            Text("Send")
        }
    }
}