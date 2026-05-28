package com.example.waiterstudy.userData

import android.content.Context
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.waiterstudy.data.FakeOrders
import java.io.File
import android.os.Environment

/*
Core of the experiment backend
*/
class UserData : ViewModel() {
    val subjects: MutableList<Subject> = mutableListOf()
    private val formatter =
        SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())

    // CURRENT RUN IN PROGRESS
    var subject = Subject(
        subjectId = "",
        layout = "",
        dateText = formatter.format(Date()),
        orders = mutableListOf()
    )

    var order = OrderData(
        orderNr = 0,
        orderId = 0,
        startTimeStamp = 0,
        endTimeStamp = 0,
        mistakes = 0,
        clicksPerScreen = mutableListOf()
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
    fun addOrderData(){
        subject.orders.add(order)
    }

    /*
    Creates fresh run
    */
    fun newSubject(subjectId: String, layout: String){
        subject = Subject(
            subjectId = subjectId,
            layout = layout,
            dateText = formatter.format(Date()),
            orders = mutableListOf()
        )
        order = OrderData(
            orderNr = 0,
            orderId = 0,
            startTimeStamp = 0,
            endTimeStamp = 0,
            mistakes = 0,
            clicksPerScreen = mutableListOf()
        )
    }

    /*
    Finalizes run
    */
    fun addSubject(){
        subjects.add(subject)
    }

    fun exportOrder(
        context: Context
    ): File {

        val file =
            File(
                context.filesDir,
                "research_data.txt"
            )

        if (!file.exists()) {

            file.appendText(
                "subjectId,layout,readableTime,orderNr,orderId,startTimeStamp,endTimeStamp,mistakes\n"
            )
        }

        file.appendText(

            "${subject.subjectId}," +
            "${subject.layout}," +
            "${subject.dateText}," +
            "${order.orderNr}," +
            "${order.orderId}," +
            "${order.startTimeStamp}," +
            "${order.endTimeStamp}," +
            "${order.mistakes}\n"

        )
        return file
    }

    fun exportClicks(context: Context): File {
        val file = File(context.filesDir, "clicks.txt")

        if (!file.exists()) {
            file.appendText("subjectId,layout,readableTime,orderNr,orderId,screen,clicks\n")
        }

        order.clicksPerScreen.forEach { session ->
            // This packs ALL clicks for this screen session into a single string cell
            val formattedClicks = session.clicks.joinToString(separator = ";") { pair ->
                "${pair.first}:${pair.second}"
            }
            val clicksCell = "\"[$formattedClicks]\""

            file.appendText(
                "${subject.subjectId},${subject.layout},${subject.dateText}," +
                        "${order.orderNr},${order.orderId},${session.screen},${clicksCell}\n"
            )
        }
        return file
    }

    fun downloadDataCsv(context: Context): File {

        val internalFile = File(
            context.filesDir,
            "research_data.txt"
        )

        // Destination in Downloads
        val downloadsDir =
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            )

        val exportFile = File(
            downloadsDir,
            "research_data_${System.currentTimeMillis()}.txt"
        )

        // Copy contents
        internalFile.copyTo(
            target = exportFile,
            overwrite = true
        )

        return exportFile
    }

    fun downloadClicksCsv(context: Context): File {

        val internalFile = File(
            context.filesDir,
            "clicks.txt"
        )

        // Destination in Downloads
        val downloadsDir =
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            )

        val exportFile = File(
            downloadsDir,
            "clicks_${System.currentTimeMillis()}.txt"
        )

        // Copy contents
        internalFile.copyTo(
            target = exportFile,
            overwrite = true
        )

        return exportFile
    }
}