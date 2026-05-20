package com.example.accommodationfinder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {

    @Insert
    suspend fun insert(listing: Listing): Long

    @Update
    suspend fun update(listing: Listing)

    @Query("SELECT * FROM listings ORDER BY createdAt DESC")
    fun getAllListings(): Flow<List<Listing>>

    @Query("SELECT * FROM listings WHERE id = :listingId LIMIT 1")
    suspend fun getListingById(listingId: Int): Listing?

    @Query("""
        SELECT * FROM listings
        WHERE price BETWEEN :minPrice AND :maxPrice
        AND location = :location
        ORDER BY price ASC
    """)
    fun filterByPriceAndLocation(
        minPrice: Double,
        maxPrice: Double,
        location: String
    ): Flow<List<Listing>>
}