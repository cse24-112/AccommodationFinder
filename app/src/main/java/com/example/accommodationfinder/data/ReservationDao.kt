package com.example.accommodationfinder.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {
    @Insert
    suspend fun insert(reservation: Reservation): Long
    
    @Query("SELECT * FROM reservations WHERE id = :reservationId")
    suspend fun getReservationById(reservationId: Int): Reservation?
    
    @Query("SELECT * FROM reservations WHERE userId = :userId ORDER BY paymentDate DESC")
    fun getUserReservations(userId: Int): Flow<List<Reservation>>
    
    @Query("SELECT * FROM reservations WHERE listingId = :listingId")
    suspend fun getReservationByListing(listingId: Int): Reservation?
    
    @Query("SELECT * FROM reservations")
    fun getAllReservations(): Flow<List<Reservation>>
    
    @Update
    suspend fun update(reservation: Reservation)
    
    @Delete
    suspend fun delete(reservation: Reservation)
}
