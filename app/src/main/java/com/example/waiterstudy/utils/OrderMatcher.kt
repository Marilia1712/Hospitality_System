package com.example.waiterstudy.utils

import com.example.waiterstudy.data.FakeOrders
import com.example.waiterstudy.data.Item

object OrderMatcher {

    fun isOrderCorrect(
        tableNumber: Int,
        currentOrder: Map<Item, Int>
    ): Boolean {

        return FakeOrders.orders.any { fakeOrder ->

            fakeOrder.tableNumber == tableNumber &&
                    fakeOrder.items == currentOrder
        }
    }
}