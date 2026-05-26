package com.example.waiterstudy.userData

/*
Instance of a single order (defined as the task going from the
table selection to sending a correct order)
* */
data class OrderData(
    val orderNr: Int,
    val orderId: Int,
    val startTimeStamp: Long,   //we need two timestamps because there are gaps between two consequent orders
    val endTimeStamp: Long,
    val mistakes: Int
)