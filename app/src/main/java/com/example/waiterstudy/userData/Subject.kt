package com.example.waiterstudy.userData


/*Instance of one full session.
Ex: Bob sends all the four experiment orders using layout with
banner at the bottom.
* */
data class Subject(
    val dateText: String, // e.g., "05-24"
    val orders: MutableList<OrderData> = mutableListOf()
)