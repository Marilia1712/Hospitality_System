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
import androidx.compose.ui.text.font.FontWeight
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
            .padding(bottom = 16.dp)
            .background(
                DarkButton,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        // BACK
        Button(
            onClick = onBack,
            modifier = Modifier
                .weight(0.15f)
                .aspectRatio(1f),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteText
            ),
            shape = RoundedCornerShape(12.dp)
        ) {

            Image(
                painter = painterResource(
                    R.drawable.back_button
                ),
                contentDescription = "Back",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // QUANTITY
        Row(
            modifier = Modifier
                .weight(0.45f)
                .background(
                    WhiteText,
                    RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 6.dp,
                    vertical = 6.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = onDecrease,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkButton
                ),
                contentPadding = PaddingValues(0.dp)
            ) {

                Text(
                    "-",
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "$quantity",
                color = DarkText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    horizontal = 10.dp
                )
            )

            Button(
                onClick = onIncrease,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkButton
                ),
                contentPadding = PaddingValues(0.dp)
            ) {

                Text(
                    "+",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // ADD
        Button(
            onClick = onAdd,
            modifier = Modifier.weight(0.25f),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueButton
            )
        ) {

            Text(
                "Add",
                fontWeight = FontWeight.Bold
            )
        }

        // CART
        Button(
            onClick = onCart,
            modifier = Modifier
                .weight(0.15f)
                .aspectRatio(1f),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteText
            ),
            shape = RoundedCornerShape(12.dp)
        ) {

            Image(
                painter = painterResource(
                    if (cartIsEmpty)
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