/*package com.example.waiterstudy.storage

import android.content.Context
import com.example.waiterstudy.data.EventLog
import com.example.waiterstudy.data.OrderResult
import com.example.waiterstudy.data.RunData
import java.io.File

object CsvExporter {

    fun exportRun(
        context: Context,
        run: RunData
    ): File {

        val file =
            File(
                context.filesDir,
                "runs.csv"
            )

        if (!file.exists()) {

            file.appendText(
                "runId,participantId,layout,ordersConfiguration,startTimestamp,endTimestamp,totalDurationMs,totalClicks,completedOrders,mistakes\n"
            )
        }

        file.appendText(

            "${run.runId}," +
                    "${run.participantId}," +
                    "${run.layout}," +
                    "${run.ordersConfiguration}," +
                    "${run.startTimestamp}," +
                    "${run.endTimestamp}," +
                    "${run.totalDurationMs}," +
                    "${run.totalClicks}," +
                    "${run.completedOrders}," +
                    "${run.mistakes}\n"

        )

        return file
    }


    fun exportOrders(
        context: Context,
        orders: List<OrderResult>
    ): File {

        val file =
            File(
                context.filesDir,
                "orders.csv"
            )

        if (!file.exists()) {

            file.appendText(

                "runId,orderNumber,tableNumber,expectedOrder,submittedOrder,correct,startTimestamp,endTimestamp,durationMs,clicks\n"

            )
        }

        orders.forEach {

            file.appendText(

                "${it.runId}," +
                        "${it.orderNumber}," +
                        "${it.tableNumber}," +
                        "${it.expectedOrder}," +
                        "${it.submittedOrder}," +
                        "${it.correct}," +
                        "${it.startTimestamp}," +
                        "${it.endTimestamp}," +
                        "${it.durationMs}," +
                        "${it.clicks}\n"

            )
        }

        return file
    }


    fun exportEvents(
        context: Context,
        events: List<EventLog>
    ): File {

        val file =
            File(
                context.filesDir,
                "events.csv"
            )

        if (!file.exists()) {

            file.appendText(

                "runId,timestamp,screen,eventType,details\n"

            )
        }

        events.forEach {

            file.appendText(

                "${it.runId}," +
                        "${it.timestamp}," +
                        "${it.screen}," +
                        "${it.eventType}," +
                        "${it.details}\n"

            )
        }

        return file
    }
}*/