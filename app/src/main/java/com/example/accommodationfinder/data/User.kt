package com.example.accommodationfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val role: String = "Student", // Student or Provider
    val createdAt: Long = System.currentTimeMillis()
)
