package com.example.accommodationfinder

object HouseImageProvider {

    private val images = listOf(
        R.drawable.house1,
        R.drawable.house2,
        R.drawable.house3
    )

    fun getRandomImage(): Int {
        return images.random()
    }
}