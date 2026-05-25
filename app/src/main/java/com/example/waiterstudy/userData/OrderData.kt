package com.example.waiterstudy.userData

import java.sql.Timestamp
import java.time.MonthDay

/*
Instance of a single order (defined as the task going from the
table selection to sending a correct order)
* */
data class OrderData(
    val orderId: Int,           //unique identifier of the order (could be useful later when we gather data)
    val startTimeStamp: Long,   //we need two timestamps because there are gaps between two consequent orders
    val endTimeStamp: Long,
    val mistakes: Int
)