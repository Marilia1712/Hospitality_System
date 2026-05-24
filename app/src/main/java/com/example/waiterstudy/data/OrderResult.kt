package com.example.waiterstudy.data

data class OrderResult(

    val runId: Int,

    val orderNumber: Int,

    val tableNumber: Int,

    val expectedOrder: String,

    val submittedOrder: String,

    val correct: Boolean,

    val startTimestamp: Long,

    val endTimestamp: Long,

    val durationMs: Long,

    val clicks: Int
)