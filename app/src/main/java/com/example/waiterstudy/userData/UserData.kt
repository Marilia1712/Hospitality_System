package com.example.waiterstudy.userData

import androidx.lifecycle.ViewModel
import com.example.waiterstudy.data.FakeOrders
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*
Core of the experiment backend
*/
class UserData : ViewModel() {

    // ALL COMPLETED RUNS
    val subjects: MutableList<Subject> = mutableListOf()

    private val formatter =
        SimpleDateFormat("MM-dd", Locale.getDefault())

    // CURRENT RUN IN PROGRESS
    var subject = Subject(
        runId = 0,
        subjectUsername = "",
        layout = "",
        dateText = formatter.format(Date()),
        orders = mutableListOf()
    )

    /*
    Checks if all orders for this run
    have been completed
    */
    fun lastOne(): Boolean {

        return subject.orders.size >= FakeOrders.orders.size

    }

    /*
    Adds completed order to current run
    */
    private var nextOrderId = 0

    fun addOrderData() {

        val newOrder = OrderData(
            orderId = nextOrderId++,
            startTimeStamp = System.currentTimeMillis(),
            endTimeStamp = 0L,
            mistakes = 0

        )

        subject.orders.add(newOrder)

    }

    /*
    Creates fresh run
    */
    fun newSubject(
        runId: Int,
        username: String,
        layout: String
    ) {

        subject = Subject(

            runId = runId,
            subjectUsername = username,
            layout = layout,
            dateText = formatter.format(Date()),
            orders = mutableListOf()

        )

    }

    /*
    Finalizes run
    */
    fun addSubject() {

        subjects.add(subject)

    }

}