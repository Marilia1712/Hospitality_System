package com.example.waiterstudy.userData

/*Instance of one full session.
Ex: Bob sends all the four experiment orders using layout with
banner at the bottom.
* */
data class Subject(
    val subjectId: String, //a number given by the participant (which in turn receives it from us)
    val layout: String, //to easily keep track of what layout was being used during this run (e.g. bottom banner)
    val dateText: String, // "MM-dd HH:mm"
    val orders: MutableList<OrderData> = mutableListOf()
)