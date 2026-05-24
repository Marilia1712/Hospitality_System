package com.example.waiterstudy.userData



data class Subject(
    val dateText: String, // e.g., "05-24"
    val orders: MutableList<OrderData> = mutableListOf()
)