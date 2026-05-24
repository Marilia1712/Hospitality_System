package com.example.waiterstudy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.waiterstudy.data.EventLog
import com.example.waiterstudy.data.OrderResult
import com.example.waiterstudy.data.RunData

class ExperimentViewModel : ViewModel() {

    var runId = 0
    var participantId = ""

    var layout = "BOTTOM"

    var totalOrdersInRun = 4
    var completedOrders = 0

    var runStartTime = 0L
    var runEndTime = 0L

    var totalClicks = 0
    var mistakes = 0

    val orderResults = mutableListOf<OrderResult>()
    val eventLogs = mutableListOf<EventLog>()

    // -------------------------
    // EVENTS / INTERACTION
    // -------------------------

    fun registerClick() {
        totalClicks++
    }

    fun registerCompletedOrder() {
        completedOrders++
    }

    fun logEvent(
        screen: String,
        eventType: String,
        details: String = ""
    ) {
        eventLogs.add(
            EventLog(
                runId = runId,
                timestamp = System.currentTimeMillis(),
                screen = screen,
                eventType = eventType,
                details = details
            )
        )
    }

    // -------------------------
    // DATA EXPORT
    // -------------------------

    fun buildRunData(): RunData {
        return RunData(
            runId = runId,
            participantId = participantId,
            layout = layout,
            ordersConfiguration = "",
            startTimestamp = runStartTime,
            endTimestamp = runEndTime,
            totalDurationMs = runEndTime - runStartTime,
            totalClicks = totalClicks,
            completedOrders = completedOrders,
            mistakes = mistakes
        )
    }

    // -------------------------
    // RESET BETWEEN RUNS
    // -------------------------

    fun resetRun() {
        runStartTime = 0L
        runEndTime = 0L
        totalClicks = 0
        mistakes = 0
        completedOrders = 0

        orderResults.clear()
        eventLogs.clear()
    }
}