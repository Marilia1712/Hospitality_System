package com.example.waiterstudy.data

data class EventLog(

    val runId: Int,

    val timestamp: Long,

    val screen: String,

    val eventType: String,

    val details: String
)