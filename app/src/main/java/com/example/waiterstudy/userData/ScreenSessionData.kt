package com.example.waiterstudy.userData

data class ScreenSessionData(
    val screen: String,
    val clicks: MutableList<Pair<Float, Float>>
)