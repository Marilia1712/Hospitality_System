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
    var orderId: Int = 0
    var startTimeStamp: Long = 0
    var endTimeStamp: Long = 0
    val subjects: MutableList<Subject> = mutableListOf()
    private val formatter =
        SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
    var mistakes: Int = 0

    // CURRENT RUN IN PROGRESS
    var subject = Subject(
        subjectId = "",
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
    fun addOrderData(){
        val newOrder = OrderData(
            orderId = orderId,
            startTimeStamp = startTimeStamp,
            endTimeStamp = endTimeStamp,
            mistakes = mistakes
        )
        subject.orders.add(newOrder)
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
                "research_data.csv"
            )

        if (!file.exists()) {

            file.appendText(
                "subjectId,layout,readableTime,orderId,startTimeStamp,endTimeStamp,mistakes\n"
            )
        }

        file.appendText(

            "${subject.subjectId}," +
            "${subject.layout}," +
            "${subject.dateText}," +
            "${orderId}," +
            "${startTimeStamp}," +
            "${endTimeStamp}," +
            "${mistakes}\n"

        )
        return file
    }

    fun DownloadCsv(context: Context): File {

        val internalFile = File(
            context.filesDir,
            "research_data.csv"
        )

        // Destination in Downloads
        val downloadsDir =
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            )

        val exportFile = File(
            downloadsDir,
            "research_data_${System.currentTimeMillis()}.csv"
        )

        // Copy contents
        internalFile.copyTo(
            target = exportFile,
            overwrite = true
        )

        return exportFile
    }
}