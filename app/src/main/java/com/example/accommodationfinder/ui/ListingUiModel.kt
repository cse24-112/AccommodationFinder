package com.example.accommodationfinder.ui.model

data class ListingUiModel(
    val id: Int,
    val title: String,
    val location: String,
    val priceText: String,
    val depositText: String,
    val amenities: String,
    val type: String,
    val availabilityDate: String,
    val imageResId: Int,
    val statusText: String,
    val statusColor: Int
)