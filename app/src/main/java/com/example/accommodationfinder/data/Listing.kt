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
    val location: String,
    val type: String,

    val amenities: String,
    val availabilityDate: String,
    val depositAmount: Double,

    val providerId: Int,

    val imageResId: Int,

    val utilities: String,
    val security: String,

    val sharingAllowed: Boolean,

    val transportRoutes: String,
    val distanceToUB: Double,

    val status: String = "Available",
)