package com.example.waiterstudy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.waiterstudy.ui.theme.*

@Composable
fun Banner1(
    selectedTable: Int?,
    onEnter: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(
                DarkButton,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = selectedTable?.let {
                "Table $it"
            } ?: "Table -",
            modifier = Modifier.weight(0.35f),
            style = MaterialTheme.typography.titleMedium,
            color = WhiteText,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = onEnter,
            enabled = selectedTable != null,
            modifier = Modifier.weight(0.65f),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueButton,
                disabledContainerColor = BlueButtonDisabled
            )
        ) {

            Text(
                text = "Enter",
                color = WhiteText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}