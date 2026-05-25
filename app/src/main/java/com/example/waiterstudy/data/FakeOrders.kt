package com.example.waiterstudy.data

object FakeOrders {

    val orders = listOf(

        // Table 2
        // 1x Coca Cola 1x Beer
        Order(
            orderId = 1,
            tableNumber = 2,
            items = mapOf(
                MenuItems.items[0] to 1, // Coca Cola
                MenuItems.items[3] to 1  // Beer
            )
        ),

        // Table 5
        // 2x Water, 1x Coffee
        Order(
            orderId = 2,
            tableNumber = 5,
            items = mapOf(
                MenuItems.items[5] to 2, // Water
                MenuItems.items[6] to 1  // Coffee
            )
        ),

        // Table 7
        // 1x Wine, 2x Spritz
        Order(
            orderId = 3,
            tableNumber = 7,
            items = mapOf(
                MenuItems.items[4] to 1, // Wine
                MenuItems.items[7] to 2  // Spritz
            )
        )
    )
}