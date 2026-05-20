package com.example.accommodationfinder.data

import com.example.accommodationfinder.R

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