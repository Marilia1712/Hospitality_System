package com.example.waiterstudy.userData

import java.sql.Timestamp
import java.time.MonthDay

/*
Instance of a single order (defined as the task going from the
table selection to sending a correct order)
* */
data class OrderData(
    val timeStamp: Long,
    val mistakes: Int
)