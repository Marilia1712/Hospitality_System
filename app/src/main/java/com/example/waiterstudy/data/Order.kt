package com.example.waiterstudy.data

data class Order(
    val tableNumber: Int,
    val items: Map<Item, Int>
)