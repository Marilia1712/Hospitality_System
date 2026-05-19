package com.example.waiterstudy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
            .padding(bottom = 24.dp)
            .background(BannerBlue)
            .padding(16.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {

        Text(
            text = selectedTable?.let { "Table $it" } ?: "Table -",
            style = MaterialTheme.typography.headlineSmall,
            color = WhiteText,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(30.dp))

        Button(
            onClick = onEnter,
            enabled = selectedTable != null,
            modifier = Modifier.weight(1f)
        ) {
            Text("Enter")
        }
    }
}