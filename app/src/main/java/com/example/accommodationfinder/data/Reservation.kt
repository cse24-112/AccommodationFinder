package com.example.accommodationfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservations")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val listingId: Int,
    val status: String = "Pending", // Pending, Completed, Cancelled
    val paymentAmount: Double,
    val referenceNumber: String,
    val paymentDate: Long = System.currentTimeMillis(),
    val notes: String = ""
)
