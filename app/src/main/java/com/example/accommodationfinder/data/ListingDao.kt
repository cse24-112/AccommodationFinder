package com.example.accommodationfinder.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {
    @Insert
    suspend fun insert(listing: Listing): Long
    
    @Query("SELECT * FROM listings ORDER BY createdAt DESC")
    fun getAllListings(): Flow<List<Listing>>
    
    @Query("SELECT * FROM listings WHERE id = :listingId")
    suspend fun getListingById(listingId: Int): Listing?
    
    @Query("""
        SELECT * FROM listings 
        WHERE price BETWEEN :minPrice AND :maxPrice
        AND location = :location
        ORDER BY price ASC
    """)
    fun filterByPriceAndLocation(minPrice: Double, maxPrice: Double, location: String): Flow<List<Listing>>
    
    @Query("""
        SELECT * FROM listings 
        WHERE availabilityDate >= :date
        ORDER BY availabilityDate ASC
    """)
    fun filterByAvailabilityDate(date: String): Flow<List<Listing>>
    
    @Query("SELECT * FROM listings WHERE status = :status")
    fun getListingsByStatus(status: String): Flow<List<Listing>>
    
    @Query("""
        SELECT * FROM listings 
        WHERE price BETWEEN :minPrice AND :maxPrice
        AND availabilityDate >= :date
        ORDER BY price ASC
    """)
    fun filterByPriceAndDate(minPrice: Double, maxPrice: Double, date: String): Flow<List<Listing>>
    
    @Update
    suspend fun update(listing: Listing)
    
    @Delete
    suspend fun delete(listing: Listing)
}
