package com.example.accommodationfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val senderId: Int,
    val receiverId: Int,
    val listingId: Int,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
