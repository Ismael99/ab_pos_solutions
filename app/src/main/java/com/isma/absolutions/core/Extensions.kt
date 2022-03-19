package com.isma.absolutions

import android.widget.DatePicker
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(pattern: String): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(this)//hace referencia a el objeto tipo Date al que se le aplique el metodo
}

fun String.getDateTime(): Map<String, Int> {
    try {
        //Extraer fecha y hora
        val dateTime = this.split(" ")
        val date = dateTime[0]
        val time = dateTime[1]
        val amOrPm = dateTime[2]
        //Para la fecha
        val arrayDate: List<String> = date.split("/")
        //Para la hora
        val arrayTime: List<String> = time.split(":")
        //Date
        val day: Int = arrayDate[0].toInt()
        val month: Int = arrayDate[1].toInt()
        val year: Int = arrayDate[2].toInt()
        //Time
        val hour: Int =
            if (amOrPm.lowercase() == "am") arrayTime[0].toInt() else arrayTime[0].toInt() + 12
        val minute: Int = arrayTime[1].toInt()
        return mapOf(
            "day" to day,
            "month" to month,
            "year" to year,
            "hour" to hour,
            "minute" to minute
        )
    } catch (e: IndexOutOfBoundsException) {
        return getFormatDateNow()
    }catch (e: NumberFormatException){
        return getFormatDateNow()
    }
}

fun DatePicker.getDateFormatString(): String {
    return "${this.dayOfMonth}/${this.month + 1}/${this.year}"
}

fun TimePicker.getTimeFormatString(): String {
    val hour = "${if (this.currentHour > 12) this.currentHour - 12 else this.currentHour}"
    val minute = "${if (this.currentMinute >= 10) this.currentMinute else "0${this.currentMinute}"}"
    return "${hour}:${minute} ${if (this.currentHour > 12) "PM" else "AM"}".toUpperCase()
}

private fun getFormatDateNow(): Map<String, Int> {
    val dateNow = Calendar.getInstance()
    return mapOf(
        "day" to dateNow.get(Calendar.DAY_OF_MONTH),
        "month" to dateNow.get(Calendar.MONTH),
        "year" to dateNow.get(Calendar.YEAR),
        "hour" to dateNow.get(Calendar.HOUR_OF_DAY),
        "minute" to dateNow.get(Calendar.MINUTE),
    )
}