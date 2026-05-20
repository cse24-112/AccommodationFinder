package com.example.accommodationfinder.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {
    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(System.currentTimeMillis())
    }

    fun generateReferenceNumber(): String {
        return "REF-" + System.currentTimeMillis().toString().takeLast(8)
    }
}
