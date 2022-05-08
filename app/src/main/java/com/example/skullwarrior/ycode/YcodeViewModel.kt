package com.example.skullwarrior.ycode

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class YcodeViewModel : ViewModel() {
    companion object {
        // These represent different important times in the game, such as game length.

        // This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        // This is the total time of the game
        private const val COUNTDOWN_TIME = 749000L

    }

    private var timer: CountDownTimer

    // Set variables for texts in the fragment

    private val _currentTime = MutableLiveData<Long>()
    private val currentTime: LiveData<Long>
        get() = _currentTime

    private val _originalPlan = MutableLiveData<String>()
    val originalPlan: LiveData<String>
        get() = _originalPlan

    private val _afterPlan = MutableLiveData<String>()
    val afterPlan: LiveData<String>
        get() = _afterPlan

    private val _beforePlan = MutableLiveData<String>()
    val beforePlan: LiveData<String>
        get() = _beforePlan

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        // find the next pulse and start a counter to it, once it ended start a new 12:30 timer
        val first = 750000 - (System.currentTimeMillis() - 1640995005000L) % 750000
        timer = object : CountDownTimer(first, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                timeReset()
            }
        }

        timer.start()
    }

    /**
     * cancel the current timer and start a new one with 12:30 minutes
     */
    private fun timeReset() {
        timer.cancel()
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                timeReset()
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    /**
     * When calculate pressed, the function takes a time and find
     * the nearest pulses (before and after)
     */
    fun onClickCalculate(hours: Long, minutes: Long) {
        // Set the strings for the return
        val beforeMinutesString: String
        val beforeHoursString: String
        val afterMinutesString: String
        val afterHoursString: String
        // Add 0 to numbers that are below 10 for better visual
        val minutesString: String = if (minutes < 10) "0$minutes" else minutes.toString()
        val hoursString: String = if (hours < 10) "0$hours" else hours.toString()
        _originalPlan.value = "$hoursString:$minutesString"

        // difference from the pulse before in seconds
        val diffFromPrevious = timeDifference(hours, minutes) / 1000
        // split to minutes and seconds
        val minutesFromPrevious = diffFromPrevious / 60
        val secondsFromPrevious = diffFromPrevious % 60
        // calculate the time subtracting the difference from the input time
        var beforeMinutes = minutes - minutesFromPrevious - 1
        var beforeHours = hours
        val beforeSeconds = 60 - secondsFromPrevious
        if (beforeMinutes < 0) {
            beforeMinutes += 60
            beforeHours -= 1
            if (beforeHours < 0) beforeHours += 24
        }
        // Add 0 to numbers that are below 10 for better visual
        beforeMinutesString = if (beforeMinutes < 10) "0$beforeMinutes" else beforeMinutes.toString()
        beforeHoursString = if (beforeHours < 10) "0$beforeHours" else beforeHours.toString()
        _beforePlan.value = "$beforeHoursString:$beforeMinutesString:$beforeSeconds"

        // calculate the time for the next pulse according to the time from the previous
        var secondsToNext = 30 - secondsFromPrevious
        var minutesToNext = 12 - minutesFromPrevious
        if (secondsToNext < 0) {
            secondsToNext += 60
            minutesToNext -= 1
        }
        // calculate the time adding the difference from the input time
        val afterMinutes = (minutes + minutesToNext) % 60
        val afterHours = (hours + (minutes + minutesToNext) / 60) % 24
        // Add 0 to numbers that are below 10 for better visual
        afterMinutesString = if (afterMinutes < 10) "0$afterMinutes" else afterMinutes.toString()
        afterHoursString = if (afterHours < 10) "0$afterHours" else afterHours.toString()
        _afterPlan.value = "$afterHoursString:$afterMinutesString:$secondsToNext"
    }

    /**
     * Find the time difference from hours:minutes to the pulse before it
     * The function find the time difference from the next pulse and the reminder from 12:30 minutes
     * diff is the difference in milliseconds
     */
    private fun timeDifference(hours: Long, minutes: Long): Long {
        val next =
            750000 - (System.currentTimeMillis() - 1640995005000L) % 750000 + System.currentTimeMillis()
        val nextArray = stringToDateComponents(convertLongToDateString(next))

        val diff = if (hours < nextArray[0] || hours == nextArray[0] && minutes < nextArray[1]) {
            (24 + hours) * 60 * 60 + minutes * 60 - nextArray[0] * 60 * 60 - nextArray[1] * 60 - nextArray[2]
        } else {
            hours * 60 * 60 + minutes * 60 - nextArray[0] * 60 * 60 - nextArray[1] * 60 - nextArray[2]
        }
        return diff * 1000 % 750000
    }

    /**
     * Takes a date string from the format "HH:mm:ss ..." and split it to [HH, mm, ss]
     */
    private fun stringToDateComponents(string: String): List<Long> {
        return listOf(
            string.subSequence(0, 2).toString().toLong(),
            string.subSequence(3, 5).toString().toLong(),
            string.subSequence(6, 8).toString().toLong()
        )
    }


    /**
     * Takes Long of milliseconds and convert it to time according to Israel time zone
     */
    @SuppressLint("SimpleDateFormat")
    fun convertLongToDateString(systemTime: Long): String {
        val sdf = SimpleDateFormat("HH:mm:ss")
        sdf.timeZone = TimeZone.getTimeZone("Asia/Jerusalem")
        return sdf.format(systemTime).toString()
    }
}

