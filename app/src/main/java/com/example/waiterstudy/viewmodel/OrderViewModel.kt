package com.example.waiterstudy.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.waiterstudy.data.Item
import com.example.waiterstudy.navigation.AppScreen
import com.example.waiterstudy.utils.OrderMatcher
import com.example.waiterstudy.userData.UserData

class OrderViewModel : ViewModel() {

    val cart = mutableStateMapOf<Item, Int>()
    var selectedTable = 0

    fun addItem(item: Item, quantity: Int) {
        if (quantity <= 0) return
        cart[item] = quantity
    }

    fun updateItem(item: Item, quantity: Int) {
        if (quantity <= 0) {
            cart.remove(item)
        } else {
            cart[item] = quantity
        }
    }
}