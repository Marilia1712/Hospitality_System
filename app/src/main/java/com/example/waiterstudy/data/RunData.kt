package com.example.waiterstudy.data

data class RunData(

    val runId: Int,

    val participantId: String,

    // TOP / BOTTOM
    val layout: String,

    val ordersConfiguration: String,

    val startTimestamp: Long,

    val endTimestamp: Long,

    val totalDurationMs: Long,

    val totalClicks: Int,

    val completedOrders: Int,

    val mistakes: Int
)