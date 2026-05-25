package com.example.waiterstudy.userData


/*Instance of one full session.
Ex: Bob sends all the four experiment orders using layout with
banner at the bottom.
* */
data class Subject(
    val runId: Int, // unique identifier of the run/subject (could be useful later when we gather data)
    val subjectUsername: String, // e.g. Bob
    val layout: String, //to easily keep track of what layout was being used during this run (e.g. bottom banner)
    val dateText: String, // e.g., "05-24"
    val orders: MutableList<OrderData> = mutableListOf()
    //TODO: consider whether to keep timestamps also for the run as a whole and not just for the single orders
)