package com.example.waiterstudy.data

data class Order(
    val orderId: Int,
    val tableNumber: Int,
    val items: Map<Item, Int>
)