package com.example.waiterstudy.utils

import com.example.waiterstudy.data.FakeOrders
import com.example.waiterstudy.data.Item
import com.example.waiterstudy.userData.UserData

object OrderMatcher {

    fun isOrderCorrect(
        tableNumber: Int,
        currentOrder: Map<Item, Int>,
        userData : UserData
    ): Boolean {
        val matchingOrder = FakeOrders.orders.find { fakeOrder ->
            fakeOrder.tableNumber == tableNumber &&
                    fakeOrder.items == currentOrder
        }

        // If an order was found, store its ID and return true
        return if (matchingOrder != null) {
            userData.order.orderId = matchingOrder.orderId
            true
        } else {
            false // No matching order found
        }
    }
}