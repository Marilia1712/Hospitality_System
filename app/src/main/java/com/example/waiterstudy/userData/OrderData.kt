package com.example.waiterstudy.userData

import java.sql.Timestamp
import java.time.MonthDay

data class OrderData(
    val timeStamp: Long,
    val mistakes: Int
)