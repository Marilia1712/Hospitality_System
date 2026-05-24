package com.example.waiterstudy.userData

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.waiterstudy.data.FakeOrders

class UserData() : ViewModel() {

    val subjects: MutableList<Subject> = mutableListOf<Subject>()
    val formatter = SimpleDateFormat("MM-dd", Locale.getDefault())

    var subject = Subject(
        dateText = formatter.format(Date()),
        orders = mutableListOf()
    )

    fun lastOne(): Boolean
    {
        return (subject.orders.size - 1 == FakeOrders.orders.size)
    }
    fun addOrderData(){
        val newOrder = OrderData(
            timeStamp = System.currentTimeMillis(),
            mistakes = 0
        )
        subject.orders.add(newOrder)
    }

    fun newSubject(){
        subject = Subject(
            dateText = formatter.format(Date()),
            orders = mutableListOf()
        )
    }

    fun addSubject(){
        subjects.add(subject)
    }
}