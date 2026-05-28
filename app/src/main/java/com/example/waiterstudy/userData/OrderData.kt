package com.example.waiterstudy.userData

/*
Instance of a single order (defined as the task going from the
table selection to sending a correct order)
* */
data class OrderData(
    var orderNr: Int,
    var orderId: Int,
    var startTimeStamp: Long,   //we need two timestamps because there are gaps between two consequent orders
    var endTimeStamp: Long,
    var mistakes: Int,
    val clicksPerScreen: MutableList<ScreenSessionData>
)