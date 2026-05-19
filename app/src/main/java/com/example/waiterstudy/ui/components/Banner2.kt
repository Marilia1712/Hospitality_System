package com.example.waiterstudy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.waiterstudy.R
import com.example.waiterstudy.ui.theme.*

@Composable
fun Banner2(
    quantity: Int,
    cartIsEmpty: Boolean,
    selectedItemName: String?,

    onBack: () -> Unit,
    onAdd: () -> Unit,
    onCart: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
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

        // QUANTITY
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Button(onClick = onDecrease) {
                Text("-")
            }

            Text(
                text = "$quantity",
                color = WhiteText,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Button(onClick = onIncrease) {
                Text("+")
            }
        }

        // ADD
        Button(
            onClick = onAdd,
            colors = ButtonDefaults.buttonColors(containerColor = BlueButton),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Add")
        }

        // CART
        Button(
            onClick = onCart,
            modifier = Modifier.size(56.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WhiteText),
            shape = RoundedCornerShape(10.dp)
        ) {
            Image(
                painter = painterResource(
                    id = if (cartIsEmpty)
                        R.drawable.empty_cart
                    else
                        R.drawable.full_cart
                ),
                contentDescription = "Cart",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}