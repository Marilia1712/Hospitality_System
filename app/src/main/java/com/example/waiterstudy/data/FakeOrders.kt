package com.example.waiterstudy.data

object FakeOrders {

    val orders = listOf(

        Order(
            tableNumber = 2,
            items = mapOf(
                MenuItems.items[0] to 1, // Coca Cola
                MenuItems.items[3] to 1  // Beer
            )
        ),

        Order(
            tableNumber = 5,
            items = mapOf(
                MenuItems.items[5] to 2, // Water
                MenuItems.items[6] to 1  // Coffee
            )
        ),

        Order(
            tableNumber = 7,
            items = mapOf(
                MenuItems.items[4] to 1, // Wine
                MenuItems.items[7] to 2  // Spritz
            )
        )
    )
}