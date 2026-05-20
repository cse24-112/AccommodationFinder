package com.example.accommodationfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listings")
data class Listing(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val price: Double,
    val location: String, // Gaborone, Tlokweng, Mogoditshane, Phase 2, Block 6, UB area
    val type: String, // Studio, 1-Bedroom, 2-Bedroom, etc
    val amenities: String, // Comma-separated: WiFi, AC, Kitchen, Laundry
    val availabilityDate: String, // YYYY-MM-DD format
    val depositAmount: Double,
    val providerId: Int,
    val imageUrl: String, // URL or resource ID
    val status: String = "Available", // Available, Reserved
    val utilities: String, // Included or Separate
    val security: String, // Gate, Guard, CCTV, Alarm
    val sharingAllowed: Boolean = true,
    val transportRoutes: String, // Comma-separated route names
    val distanceToUB: Double, // Distance in km
    val createdAt: Long = System.currentTimeMillis()
)
