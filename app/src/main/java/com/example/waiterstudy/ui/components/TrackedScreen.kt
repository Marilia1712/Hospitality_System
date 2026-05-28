package com.example.waiterstudy.ui.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.waiterstudy.userData.ScreenSessionData
import com.example.waiterstudy.userData.UserData
import androidx.compose.ui.input.pointer.changedToDown

@Composable
fun TrackedScreen(
    screenName: String,
    userData: UserData,
    content: @Composable () -> Unit
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    LaunchedEffect(screenName) {
        val sessionExists = userData.order.clicksPerScreen.any { it.screen == screenName }
        if (!sessionExists) {
            userData.order.clicksPerScreen.add(
                ScreenSessionData(screen = screenName, clicks = mutableListOf())
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                size = coordinates.size
            }
            .pointerInput(screenName, size) {
                // awaitEachGesture safely manages the loop and automatically handles bounds/cancellation resets
                awaitEachGesture {
                    val event = awaitPointerEvent(PointerEventPass.Initial)

                    // Check if it's the start of a touch/click action
                    if (event.changes.any { it.changedToDown() }) {
                        val position = event.changes.first().position

                        if (size.width > 0 && size.height > 0) {
                            val relativeX = position.x / size.width
                            val relativeY = position.y / size.height

                            val currentSession = userData.order.clicksPerScreen.find { it.screen == screenName }
                            currentSession?.clicks?.add(Pair(relativeX, relativeY))
                        }
                    }
                }
            }
    ) {
        content()
    }
}