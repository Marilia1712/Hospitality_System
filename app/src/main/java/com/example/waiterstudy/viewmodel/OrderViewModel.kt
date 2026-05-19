package com.example.waiterstudy.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.waiterstudy.data.Item

class OrderViewModel : ViewModel() {

    var selectedTable = 0

    val cart = mutableStateMapOf<Item, Int>()

    fun addItem(
        item: Item,
        quantity: Int
    ) {

        if (quantity <= 0) return

        cart[item] = quantity
    }

    fun clearOrder() {

        selectedTable = 0
        cart.clear()
    }

    fun updateItem(item: Item, quantity: Int) {

        if (quantity <= 0) {
            cart.remove(item)
        } else {
            cart[item] = quantity
        }
    }
}